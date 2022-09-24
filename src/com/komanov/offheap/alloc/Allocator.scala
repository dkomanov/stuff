package com.komanov.offheap.alloc

import sun.misc.Unsafe

object Allocator {
  val unsafe: Unsafe = {
    val field = classOf[Unsafe].getDeclaredField("theUnsafe")
    field.setAccessible(true)
    field.get(null).asInstanceOf[Unsafe]
  }

  def getLong(address: Long): Long = {
    unsafe.getLong(address)
  }

  def getDouble(address: Long): Double = {
    unsafe.getDouble(address);
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
