package com.komanov.offheap

import sun.misc.Unsafe

object Allocator {
  private val unsafe = {
    val field = classOf[Unsafe].getDeclaredField("theUnsafe")
    field.setAccessible(true)
    field.get(null).asInstanceOf[Unsafe]
  }

  def getLong(address: Long): Long = {
    unsafe.getLong(address)
  }

  def setLong(address: Long, value: Long): Unit = {
    unsafe.putLong(address, value)
  }

  def alloc(bytes: Long): Long = {
    unsafe.allocateMemory(bytes)
  }

  def release(address: Long): Unit = {
    unsafe.freeMemory(address)
  }
}
