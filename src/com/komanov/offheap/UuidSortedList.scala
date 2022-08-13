package com.komanov.offheap

import scala.jdk.CollectionConverters._
import java.util.UUID

object UuidSortedList {
  def offHeap(list: Seq[UUID]): OffHeapBasedUuidSet = {
    val address = Allocator.alloc(list.size * 16)
    list.sorted.zipWithIndex.foreach { case (uuid, index) =>
      val current = address + (index * 16)
      Allocator.setLong(current, uuid.getMostSignificantBits)
      Allocator.setLong(current + 8, uuid.getLeastSignificantBits)
    }
    new OffHeapBasedUuidSet(address, list.size)
  }

  def netty4Composite(list: Seq[UUID]): Netty4UuidSet = {
    val alloc = io.netty.buffer.PooledByteBufAllocator.DEFAULT
    val buf = alloc.compositeDirectBuffer(Int.MaxValue)
    list.sorted.grouped(250).foreach { list =>
      val b = alloc.directBuffer(4000, 4000)
      list.foreach { uuid =>
        b.writeLong(uuid.getMostSignificantBits)
        b.writeLong(uuid.getLeastSignificantBits)
      }
      buf.addComponent(b)
    }
    new Netty4UuidSet(buf, list.size)
  }

  def netty4Single(list: Seq[UUID]): Netty4UuidSet = {
    val alloc = io.netty.buffer.PooledByteBufAllocator.DEFAULT
    val buf = alloc.directBuffer(list.size * 16)
    list.sorted.foreach { uuid =>
      buf.writeLong(uuid.getMostSignificantBits)
      buf.writeLong(uuid.getLeastSignificantBits)
    }
    new Netty4UuidSet(buf, list.size)
  }

  def netty5Composite(list: Seq[UUID]): Netty5UuidSet = {
    val alloc = io.netty5.buffer.api.DefaultBufferAllocators.offHeapAllocator()
    val buffers = list.sorted.grouped(250).map { list =>
      val b = alloc.allocate(4000)
      list.foreach { uuid =>
        b.writeLong(uuid.getMostSignificantBits)
        b.writeLong(uuid.getLeastSignificantBits)
      }
      b
    }.toSeq
    val buf = alloc.compose(buffers.map(_.send()).asJava)
    new Netty5UuidSet(buf, list.size)
  }

  def netty5Single(list: Seq[UUID]): Netty5UuidSet = {
    val alloc = io.netty5.buffer.api.DefaultBufferAllocators.offHeapAllocator()
    val buf = alloc.allocate(list.size * 16)
    list.sorted.foreach { uuid =>
      buf.writeLong(uuid.getMostSignificantBits)
      buf.writeLong(uuid.getLeastSignificantBits)
    }
    new Netty5UuidSet(buf, list.size)
  }

  def heap(list: Seq[UUID]): ArrayBasedUuidSet = {
    val array = new Array[Long](list.size * 2)
    var index = 0
    list.sorted.foreach { uuid =>
      array.update(index, uuid.getMostSignificantBits)
      array.update(index + 1, uuid.getLeastSignificantBits)
      index += 2
    }
    new ArrayBasedUuidSet(array)
  }
}
