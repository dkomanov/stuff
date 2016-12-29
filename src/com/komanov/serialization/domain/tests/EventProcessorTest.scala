package com.komanov.serialization.domain

import com.komanov.serialization.converters.TestData
import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.core.Fragments

class EventProcessorTest extends SpecificationWithJUnit {

  "apply/unapply" should {
    Fragments.foreach(TestData.sites) { case (name, site) =>
      s"serialize and deserialize a site [$name]" in {
        val parsed = EventProcessor.apply(EventProcessor.unapply(site))
        parsed must be_===(site)
      }
    }
  }

}
