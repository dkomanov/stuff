package com.komanov.stringformat.tests

import com.komanov.stringformat.OptimizedConcatenation1

import org.specs2.mutable.SpecificationWithJUnit

class OptimizedConcatenation1Test extends SpecificationWithJUnit {

  "concat" should {
    "objects" >> {
      OptimizedConcatenation1.concat(o1, o2) must be_===(s1 + s2)
      OptimizedConcatenation1.concat(o1, o2, o3) must be_===(s1 + s2 + s3)
      OptimizedConcatenation1.concat(o1, o2, o3, o4) must be_===(s1 + s2 + s3 + s4)
      OptimizedConcatenation1.concat(o1, o2, o3, o4, o5) must be_===(s1 + s2 + s3 + s4 + s5)
      OptimizedConcatenation1.concat(o1, o2, o3, o4, o5, o6) must be_===(s1 + s2 + s3 + s4 + s5 + s6)
      OptimizedConcatenation1.concatObjects(o1, o2, o3, o4, o5, o1, o2, o3, o4, o5) must be_===(s1 + s2 + s3 + s4 + s5 + s1 + s2 + s3 + s4 + s5)
    }

    "strings" >> {
      OptimizedConcatenation1.concat(s1, s2) must be_===(s1 + s2)
      OptimizedConcatenation1.concat(s1, s2, s3) must be_===(s1 + s2 + s3)
      OptimizedConcatenation1.concat(s1, s2, s3, s4) must be_===(s1 + s2 + s3 + s4)
      OptimizedConcatenation1.concat(s1, s2, s3, s4, s5) must be_===(s1 + s2 + s3 + s4 + s5)
      OptimizedConcatenation1.concat(s1, s2, s3, s4, s5, s6) must be_===(s1 + s2 + s3 + s4 + s5 + s6)
      OptimizedConcatenation1.concatStrings(s1, s2, s3, s4, s5, s1, s2, s3, s4, s5) must be_===(s1 + s2 + s3 + s4 + s5 + s1 + s2 + s3 + s4 + s5)
    }

    "replace null with 'null' string" >> {
      OptimizedConcatenation1.concat(null, Int.box(1)) must be_===("null1")
      OptimizedConcatenation1.concat(null, "1") must be_===("null1")
    }
  }

  val o1: Object = Int.box(10000)
  val o2: Object = Int.box(200000)
  val o3: Object = Int.box(3000000)
  val o4: Object = Int.box(40000000)
  val o5: Object = Int.box(500000000)
  val o6: Object = Int.box(60000000)
  val o7: Object = Int.box(7000000)

  val s1 = "10000"
  val s2 = "200000"
  val s3 = "3000000"
  val s4 = "40000000"
  val s5 = "500000000"
  val s6 = "60000000"
  val s7 = "7000000"

}
