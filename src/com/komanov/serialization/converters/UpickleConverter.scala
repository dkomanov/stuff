package com.komanov.serialization.converters

import com.komanov.serialization.converters.UpickleShared._
import com.komanov.serialization.converters.api.MyConverter
import com.komanov.serialization.domain._
import ujson.JsVisitor
import upickle.core.{ArrVisitor, ByteBuilder, CharBuilder, ObjVisitor}
import upickle.default._

import java.io.OutputStream
import java.time.Instant

object UpickleJsonConverter extends MyConverter {
  override def toByteArray(event: SiteEvent): Array[Byte] = writeToByteArray(event)

  override def siteEventFromByteArray(clazz: Class[_], bytes: Array[Byte]): SiteEvent = read[SiteEvent](bytes)

  override def toByteArray(site: Site): Array[Byte] = writeToByteArray(site)

  override def fromByteArray(bytes: Array[Byte]): Site = read[Site](bytes)
}

object UpicklePooledJsonConverter extends MyConverter {
  private val tl = new ThreadLocal[(ByteBuilder, CharBuilder)]() {
    override def initialValue(): (ByteBuilder, CharBuilder) = (new ByteBuilder(32 * 1024), new CharBuilder(1024))
  }

  override def toByteArray(event: SiteEvent): Array[Byte] = writeFast(event)

  override def siteEventFromByteArray(clazz: Class[_], bytes: Array[Byte]): SiteEvent = read[SiteEvent](bytes)

  override def toByteArray(site: Site): Array[Byte] = writeFast(site)

  override def fromByteArray(bytes: Array[Byte]): Site = read[Site](bytes)

  private def writeFast[T: Writer](v: T): Array[Byte] = {
    val (bb, cb) = tl.get()
    bb.reset()
    cb.reset()
    transform(v).to(new MyVisitor(bb, cb))
    val r = new Array[Byte](bb.getLength)
    val os = new OutputStream {
      override def write(b: Array[Byte], off: Int, len: Int): Unit = {
        System.arraycopy(b, 0, r, 0, len)
      }
      override def write(b: Int): Unit = ???
    }
    bb.writeOutToIfLongerThan(os, 0)
    r
  }

  class MyVisitor(elemBuilder: ByteBuilder, unicodeCharBuilder: CharBuilder) extends JsVisitor[ByteBuilder, ByteBuilder]{
    private[this] var depth: Int = 0

    private[this] var visitingKey = false

    private[this] var commaBuffered = false
    private[this] var quoteBuffered = false

    def flushBuffer() = {
      if (commaBuffered) {
        commaBuffered = false
        elemBuilder.append(',')
      }
      if (quoteBuffered) {
        quoteBuffered = false
        elemBuilder.append('"')
      }
    }

    def visitArray(length: Int, index: Int) = new ArrVisitor[ByteBuilder, ByteBuilder] {
      flushBuffer()
      elemBuilder.append('[')

      depth += 1
      def subVisitor = MyVisitor.this
      def visitValue(v: ByteBuilder, index: Int): Unit = {
        flushBuffer()
        commaBuffered = true
      }
      def visitEnd(index: Int) = {
        commaBuffered = false
        depth -= 1
        elemBuilder.append(']')
        elemBuilder
      }
    }

    def visitJsonableObject(length: Int, index: Int) = new ObjVisitor[ByteBuilder, ByteBuilder] {
      flushBuffer()
      elemBuilder.append('{')
      depth += 1
      def subVisitor = MyVisitor.this
      def visitKey(index: Int) = {
        quoteBuffered = true
        visitingKey = true
        MyVisitor.this
      }
      def visitKeyValue(s: Any): Unit = {
        elemBuilder.append('"')
        visitingKey = false
        elemBuilder.append(':')
      }
      def visitValue(v: ByteBuilder, index: Int): Unit = {
        commaBuffered = true
      }
      def visitEnd(index: Int) = {
        commaBuffered = false
        depth -= 1
        elemBuilder.append('}')
        elemBuilder
      }
    }

    def visitNull(index: Int) = {
      flushBuffer()
      elemBuilder.ensureLength(4)
      elemBuilder.appendUnsafe('n')
      elemBuilder.appendUnsafe('u')
      elemBuilder.appendUnsafe('l')
      elemBuilder.appendUnsafe('l')
      elemBuilder
    }

    def visitFalse(index: Int) = {
      flushBuffer()
      elemBuilder.ensureLength(5)
      elemBuilder.appendUnsafe('f')
      elemBuilder.appendUnsafe('a')
      elemBuilder.appendUnsafe('l')
      elemBuilder.appendUnsafe('s')
      elemBuilder.appendUnsafe('e')
      elemBuilder
    }

    def visitTrue(index: Int) = {
      flushBuffer()
      elemBuilder.ensureLength(4)
      elemBuilder.appendUnsafe('t')
      elemBuilder.appendUnsafe('r')
      elemBuilder.appendUnsafe('u')
      elemBuilder.appendUnsafe('e')
      elemBuilder
    }

    def visitFloat64StringParts(s: CharSequence, decIndex: Int, expIndex: Int, index: Int) = {
      flushBuffer()
      elemBuilder.ensureLength(s.length())
      var i = 0
      val sLength = s.length
      while(i < sLength){
        elemBuilder.appendUnsafeC(s.charAt(i))
        i += 1
      }
      elemBuilder
    }

    override def visitFloat32(d: Float, index: Int) = {
      d match{
        case Float.PositiveInfinity => visitNonNullString("Infinity", -1)
        case Float.NegativeInfinity => visitNonNullString("-Infinity", -1)
        case d if java.lang.Float.isNaN(d) => visitNonNullString("NaN", -1)
        case d =>
          val i = d.toInt
          if (d == i) visitFloat64StringParts(i.toString, -1, -1, index)
          else super.visitFloat32(d, index)
          flushBuffer()
      }
      elemBuilder
    }

    override def visitFloat64(d: Double, index: Int) = {
      d match{
        case Double.PositiveInfinity => visitNonNullString("Infinity", -1)
        case Double.NegativeInfinity => visitNonNullString("-Infinity", -1)
        case d if java.lang.Double.isNaN(d) => visitNonNullString("NaN", -1)
        case d =>
          val i = d.toInt
          if (d == i) visitFloat64StringParts(i.toString, -1, -1, index)
          else super.visitFloat64(d, index)
          flushBuffer()
      }
      elemBuilder
    }

    def visitString(s: CharSequence, index: Int) = {

      if (s eq null) visitNull(index)
      else visitNonNullString(s, index)
    }

    def visitNonNullString(s: CharSequence, index: Int) = {
      flushBuffer()

      upickle.core.RenderUtils.escapeByte(
        unicodeCharBuilder, elemBuilder, s, false, !visitingKey
      )

      elemBuilder
    }
  }}

object UpickleMsgpackConverter extends MyConverter {
  override def toByteArray(event: SiteEvent): Array[Byte] = writeBinary(event)

  override def siteEventFromByteArray(clazz: Class[_], bytes: Array[Byte]): SiteEvent = readBinary[SiteEvent](bytes)

  override def toByteArray(site: Site): Array[Byte] = writeBinary(site)

  override def fromByteArray(bytes: Array[Byte]): Site = readBinary[Site](bytes)
}

object UpickleShared {
  implicit val rwInstant: ReadWriter[Instant] = readwriter[Long].bimap(ConversionUtils.instantToLong, ConversionUtils.longToInstance)
  implicit val rwSiteType: ReadWriter[SiteType] = readwriter[String].bimap(_.name, SiteType.valueOf)
  implicit val rwSiteFlag: ReadWriter[SiteFlag] = readwriter[String].bimap(_.name, SiteFlag.valueOf)
  implicit val pctSiteType: ReadWriter[PageComponentType] = readwriter[String].bimap(_.name, PageComponentType.valueOf)
  implicit val rwDomain: ReadWriter[Domain] = macroRW
  implicit val rwMetaTag: ReadWriter[MetaTag] = macroRW
  implicit val rwPageComponentData: ReadWriter[PageComponentData] = ReadWriter.merge(
    macroRW[TextComponentData],
    macroRW[ButtonComponentData],
    macroRW[BlogComponentData],
  )
  implicit val rwPageComponentPosition: ReadWriter[PageComponentPosition] = macroRW
  implicit val rwPageComponent: ReadWriter[PageComponent] = macroRW
  implicit val rwPage: ReadWriter[Page] = macroRW
  implicit val rwEntryPoint: ReadWriter[EntryPoint] = ReadWriter.merge(
    macroRW[DomainEntryPoint],
    macroRW[FreeEntryPoint],
  )
  implicit val rwSite: ReadWriter[Site] = macroRW
  implicit val rwEvent: ReadWriter[SiteEvent] = ReadWriter.merge(
    macroRW[SiteCreated],
    macroRW[SiteNameSet],
    macroRW[SiteDescriptionSet],
    macroRW[SiteRevisionSet],
    macroRW[SitePublished],
    macroRW[SiteUnpublished],
    macroRW[SiteFlagAdded],
    macroRW[SiteFlagRemoved],
    macroRW[DomainAdded],
    macroRW[DomainRemoved],
    macroRW[PrimaryDomainSet],
    macroRW[DefaultMetaTagAdded],
    macroRW[DefaultMetaTagRemoved],
    macroRW[PageAdded],
    macroRW[PageRemoved],
    macroRW[PageNameSet],
    macroRW[PageMetaTagAdded],
    macroRW[PageMetaTagRemoved],
    macroRW[PageComponentAdded],
    macroRW[PageComponentRemoved],
    macroRW[PageComponentPositionSet],
    macroRW[PageComponentPositionReset],
    macroRW[TextComponentDataSet],
    macroRW[ButtonComponentDataSet],
    macroRW[BlogComponentDataSet],
    macroRW[DomainEntryPointAdded],
    macroRW[FreeEntryPointAdded],
    macroRW[EntryPointRemoved],
    macroRW[PrimaryEntryPointSet]
  )
}
