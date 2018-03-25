package com.komanov.serialization.jmh;

import com.komanov.serialization.domain.Site;
import com.komanov.serialization.domain.testdata.TestData$;

public enum InputType {
    _1_K(TestData$.MODULE$.site1k()),
    _2_K(TestData$.MODULE$.site2k()),
    _4_K(TestData$.MODULE$.site4k()),
    _8_K(TestData$.MODULE$.site8k()),
    _64_K(TestData$.MODULE$.site64k()),
    /**/;

    public final Site site;

    InputType(Site site) {
        this.site = site;
    }
}
