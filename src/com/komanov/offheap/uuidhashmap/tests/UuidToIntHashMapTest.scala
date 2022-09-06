package com.komanov.offheap.uuidhashmap.tests

import scala.jdk.CollectionConverters._
import com.komanov.offheap.uuidhashmap.UuidToIntHashMap
import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.core.Fragments

import java.util.UUID.randomUUID
import java.util.{Collections, OptionalInt, UUID}

class UuidToIntHashMapTest extends SpecificationWithJUnit {

  sequential

  Fragments.foreach(Seq(
    TestCase("on-heap", map => UuidToIntHashMap.heap(map)),
    TestCase("off-heap", map => UuidToIntHashMap.offHeap(map)),
    TestCase("off-heap with padding", map => UuidToIntHashMap.offHeapWithPadding(map)),
  )) { case TestCase(name, make) =>
    name >> {
      "succeed for empty Map" >> {
        val map = make(Collections.emptyMap())
        map.get(randomUUID) must beOptEmpty
        map.getClass.getSimpleName must contain("EmptyMap")
      }

      "succeed for a single item" >> {
        val key = randomUUID
        val value = 666
        val map = make(Collections.singletonMap(key, value))
        try {
          map.get(randomUUID) must beOptEmpty
          map.get(key) must beOptOf(value)
        } finally {
          map.destroy()
        }
      }

      "succeed for a multiple items" >> {
        val items = (1 to 10).map(v => randomUUID -> v)
        val map = make(items.map(t => t._1 -> Int.box(t._2)).toMap.asJava)
        try {
          map.get(randomUUID) must beOptEmpty
          map.get(items.head._1) must beOptOf(items.head._2)
          items.map(_._1).map(map.get) must contain(exactly(items.map(_._2).map(beOptOf): _*)).inOrder
        } finally {
          map.destroy()
        }
      }
    }
  }

  private def beOptEmpty = ===(OptionalInt.empty())
  private def beOptOf(v: Int) = ===(OptionalInt.of(v))

  private case class TestCase(name: String, f: java.util.Map[UUID, Integer] => UuidToIntHashMap)

}
