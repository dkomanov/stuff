package com.komanov.serialization.converters

import java.nio.ByteBuffer
import java.time.Instant
import java.util

import boopickle.Default._
import boopickle.{BufferPool, DecoderSize, EncoderSize}
import com.komanov.serialization.domain._

/** https://github.com/ochrons/boopickle */
object BoopickleConverter extends MyConverter {

  implicit def pickleState = new PickleState(new EncoderSize, false, false)
  implicit val unpickleState = (bb: ByteBuffer) => new UnpickleState(new DecoderSize(bb), false, false)

  BufferPool.disable()

  implicit val instantPickler = transformPickler[Instant, Long](t => Instant.ofEpochMilli(t))(_.toEpochMilli)

  implicit val pageComponentTypePickler = transformPickler(PageComponentType.valueOf)(_.name())
  implicit val siteFlagPickler = transformPickler(SiteFlag.valueOf)(_.name())
  implicit val siteTypePickler = transformPickler(SiteType.valueOf)(_.name())

  implicit val entryPointPickler = compositePickler[EntryPoint]
    .addConcreteType[DomainEntryPoint]
    .addConcreteType[FreeEntryPoint]

  implicit val pageComponentDataPickler = compositePickler[PageComponentData]
    .addConcreteType[TextComponentData]
    .addConcreteType[ButtonComponentData]
    .addConcreteType[BlogComponentData]

  implicit val siteEventPickler = compositePickler[SiteEvent]
    .addConcreteType[SiteCreated]
    .addConcreteType[SiteNameSet]
    .addConcreteType[SiteDescriptionSet]
    .addConcreteType[SiteRevisionSet]
    .addConcreteType[SitePublished]
    .addConcreteType[SiteUnpublished]
    .addConcreteType[SiteFlagAdded]
    .addConcreteType[SiteFlagRemoved]
    .addConcreteType[DomainAdded]
    .addConcreteType[DomainRemoved]
    .addConcreteType[PrimaryDomainSet]
    .addConcreteType[DefaultMetaTagAdded]
    .addConcreteType[DefaultMetaTagRemoved]
    .addConcreteType[PageAdded]
    .addConcreteType[PageRemoved]
    .addConcreteType[PageNameSet]
    .addConcreteType[PageMetaTagAdded]
    .addConcreteType[PageMetaTagRemoved]
    .addConcreteType[PageComponentAdded]
    .addConcreteType[PageComponentRemoved]
    .addConcreteType[PageComponentPositionSet]
    .addConcreteType[PageComponentPositionReset]
    .addConcreteType[TextComponentDataSet]
    .addConcreteType[ButtonComponentDataSet]
    .addConcreteType[BlogComponentDataSet]
    .addConcreteType[DomainEntryPointAdded]
    .addConcreteType[FreeEntryPointAdded]
    .addConcreteType[EntryPointRemoved]
    .addConcreteType[PrimaryEntryPointSet]

  override def toByteArray(site: Site): Array[Byte] = {
    val bb = Pickle.intoBytes(site)
    val a = bbToArray(bb)
    BufferPool.release(bb)
    a
  }

  override def fromByteArray(bytes: Array[Byte]): Site = {
    Unpickle.apply[Site].fromBytes(ByteBuffer.wrap(bytes))
  }

  override def toByteArray(event: SiteEvent): Array[Byte] = {
    val bb = Pickle.intoBytes(event)
    val a = bbToArray(bb)
    BufferPool.release(bb)
    a
  }

  override def siteEventFromByteArray(clazz: Class[_], bytes: Array[Byte]): SiteEvent = {
    Unpickle.apply[SiteEvent].fromBytes(ByteBuffer.wrap(bytes))
  }

  private def bbToArray(bb: ByteBuffer) = {
    util.Arrays.copyOfRange(bb.array(), 0, bb.limit())
  }
}
