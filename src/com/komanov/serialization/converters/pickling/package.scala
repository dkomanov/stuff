package com.komanov.serialization.converters

import java.time.Instant
import java.util.UUID

import com.komanov.serialization.domain.SiteType

import scala.pickling.Defaults._
import scala.pickling.{FastTypeTag, _}
import scala.reflect.ClassTag

package object pickling {

  implicit class RichBuilder(val builder: PBuilder) extends AnyVal {
    def writeValue[T](name: String, f: => T, p: Pickler[T]) = {
      builder.putField(name, p.pickle(f, _))
    }
  }

  implicit class RichReader(val reader: PReader) extends AnyVal {
    def readValue[T](name: String, unpickler: Unpickler[T]): T = {
      unpickler.unpickleEntry(reader.readField(name)).asInstanceOf[T]
    }

    def readValue[T](name: String, setter: T => Unit, unpickler: Unpickler[T]): Unit = {
      setter(readValue(name, unpickler))
    }
  }

  implicit def optionPickler[T: FastTypeTag](implicit pickler: Pickler[T], unpickler: Unpickler[T]): Pickler[Option[T]] with Unpickler[Option[T]] = new AbstractPicklerUnpickler[Option[T]] {
    override def tag = implicitly[FastTypeTag[Option[T]]]

    override def pickle(v: Option[T], builder: PBuilder): Unit = {
      builder.beginEntry(v, tag)
      if (v.isEmpty) {
        Defaults.nullPickler.pickle(null, builder)
      } else {
        pickler.pickle(v.get, builder)
      }
      builder.endEntry()
    }

    override def unpickle(tag: String, reader: PReader): Any = {
      if (tag == FastTypeTag.Null.key) {
        None
      } else {
        Option(unpickler.unpickleEntry(reader))
      }
    }
  }

  class NullPickler[T: FastTypeTag] extends AbstractPicklerUnpickler[T] {
    override def unpickle(tag: String, reader: PReader): Any = {
      null
    }

    override def tag: FastTypeTag[T] = implicitly[FastTypeTag[T]]

    override def pickle(v: T, builder: PBuilder): Unit = {
      builder.beginEntry(v, tag)
      builder.endEntry()
    }
  }

  abstract class PicklerUnpicklerBase[T] extends AbstractPicklerUnpickler[T] {
    final override def pickle(v: T, builder: PBuilder): Unit = {
      builder.beginEntry(v, tag)
      pickleInternal(v, builder)
      builder.endEntry()
    }

    protected def pickleInternal(v: T, builder: PBuilder): Unit
  }

  implicit object UuidPickler extends PicklerUnpicklerBase[UUID] {
    override def tag: FastTypeTag[UUID] = FastTypeTag[UUID]

    override protected def pickleInternal(v: UUID, builder: PBuilder): Unit = {
      builder.writeValue("m", v.getMostSignificantBits, longPickler)
      builder.writeValue("l", v.getLeastSignificantBits, longPickler)
    }

    override def unpickle(tag: String, reader: PReader): Any = {
      new UUID(
        reader.readValue("m", longPickler),
        reader.readValue("l", longPickler)
      )
    }
  }

  class JavaEnumPickler[T <: Enum[T] : ClassTag : FastTypeTag] extends PicklerUnpicklerBase[T] {
    override def tag = implicitly[FastTypeTag[T]]

    override protected def pickleInternal(v: T, builder: PBuilder): Unit = {
      builder.writeValue("x", v.toString, stringPickler)
    }

    override def unpickle(tag: String, reader: PReader): Any = {
      val id = reader.readValue("x", stringPickler)
      val clazz = implicitly[ClassTag[T]].runtimeClass
      // Hack to fck compiler
      Enum.valueOf(clazz.asInstanceOf[Class[SiteType]], id)
    }
  }

  implicit object dateTimePickler extends PicklerUnpicklerBase[Instant] {
    override def tag = FastTypeTag[Instant]

    override protected def pickleInternal(v: Instant, builder: PBuilder): Unit = {
      builder.writeValue("x", v.toEpochMilli, longPickler)
    }

    override def unpickle(tag: String, reader: PReader): Any = {
      Instant.ofEpochMilli(reader.readValue("x", longPickler))
    }
  }

}
