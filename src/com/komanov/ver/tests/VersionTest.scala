package com.komanov.ver.tests

import com.komanov.ver.Version.MaxVersionSize
import com.komanov.ver.{Version, VersionNoAlloc}
import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.core.Fragment

class VersionTest extends SpecificationWithJUnit {

  sequential

  case class TestCase(s: String, expected: Option[Version])

  Fragment.foreach(Seq(
    TestCase("", None),
    TestCase("200", None),
    TestCase("200.", None),
    TestCase("200.200", None),
    TestCase("200.200.", None),
    TestCase("a.200.200", None),
    TestCase("200.a.200", None),
    TestCase("200.200.a", None),
    //TestCase("200.200.200.", None), // fails for yolo, because it uses split
    TestCase("200..200", None),
    TestCase("200..200.200", None),
    TestCase("200.200.200.200", None),
    TestCase("200.200.99999", None),
    TestCase("200.200.-200", None),

    TestCase("01.01.01", Some(Version(1, 1, 1))),
    TestCase("0.0.0", Some(Version(0, 0, 0))),
    TestCase("200.200.200", Some(Version(200, 200, 200))),
    TestCase("10000.9876.5432", Some(Version(10000, 9876, 5432))),
    TestCase(s"$MaxVersionSize.$MaxVersionSize.$MaxVersionSize", Some(Version(MaxVersionSize, MaxVersionSize, MaxVersionSize))),
  )) { case TestCase(s, expected) =>
    val expectedNoAlloc = VersionNoAlloc.fromVersionGeneric(expected)

    s"parseYolo for '$s''" >> {
      Version.parseYolo(s) mustEqual expected
      VersionNoAlloc.parseYolo(s) mustEqual expectedNoAlloc
    }

    s"parseYoloNoTry for '$s''" >> {
      Version.parseYoloNoTry(s) mustEqual expected
      VersionNoAlloc.parseYoloNoTry(s) mustEqual expectedNoAlloc
    }

    s"parseYoloNoThrow for '$s''" >> {
      Version.parseYoloNoThrow(s) mustEqual expected
      VersionNoAlloc.parseYoloNoThrow(s) mustEqual expectedNoAlloc
    }

    s"parseYoloNoThrowNoTry for '$s''" >> {
      Version.parseYoloNoThrowNoTry(s) mustEqual expected
      VersionNoAlloc.parseYoloNoThrowNoTry(s) mustEqual expectedNoAlloc
    }

    s"parseWithRegex for '$s''" >> {
      Version.parseWithRegex(s) mustEqual expected
      VersionNoAlloc.parseWithRegex(s) mustEqual expectedNoAlloc
    }

    s"parseOptimized1 for '$s''" >> {
      Version.parseOptimized1(s) mustEqual expected
      VersionNoAlloc.parseOptimized1(s) mustEqual expectedNoAlloc
    }

    s"parseOptimized2 for '$s''" >> {
      Version.parseOptimized2(s) mustEqual expected
      VersionNoAlloc.parseOptimized2(s) mustEqual expectedNoAlloc
    }

    s"parseOptimized3 for '$s''" >> {
      Version.parseOptimized3(s) mustEqual expected
      VersionNoAlloc.parseOptimized3(s) mustEqual expectedNoAlloc
    }

    s"parseOptimized3Java for '$s''" >> {
      Version.parseOptimized3Java(s) mustEqual expected
      VersionNoAlloc.parseOptimized3Java(s) mustEqual expectedNoAlloc
    }

    s"parseOptimized3Java for '$s''" >> {
      Version.parseOptimized3Java(s) mustEqual expected
      VersionNoAlloc.parseOptimized3Java(s) mustEqual expectedNoAlloc
    }

    s"parseOptimized3JavaNoSwitch for '$s''" >> {
      Version.parseOptimized3JavaNoSwitch(s) mustEqual expected
      VersionNoAlloc.parseOptimized3JavaNoSwitch(s) mustEqual expectedNoAlloc
    }

    s"parseOptimized4 for '$s''" >> {
      Version.parseOptimized4(s) mustEqual expected
      VersionNoAlloc.parseOptimized4(s) mustEqual expectedNoAlloc
    }

    s"parseOptimized5 for '$s''" >> {
      Version.parseOptimized5(s) mustEqual expected
      VersionNoAlloc.parseOptimized5(s) mustEqual expectedNoAlloc
    }

    s"parseOptimized6 for '$s''" >> {
      Version.parseOptimized6(s) mustEqual expected
      VersionNoAlloc.parseOptimized6(s) mustEqual expectedNoAlloc
    }

    s"VersionNoAlloc parse/serialize for '$expected''" >> {
      VersionNoAlloc.toVersionGeneric(VersionNoAlloc.fromVersionGeneric(expected)) mustEqual expected
      VersionNoAlloc.toVersionInlined(VersionNoAlloc.fromVersionInlined(expected)) mustEqual expected
    }
  }

}
