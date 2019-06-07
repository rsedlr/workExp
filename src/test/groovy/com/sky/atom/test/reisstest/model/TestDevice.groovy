package com.sky.atom.test.reisstest

import com.sky.atom.test.reisstest.model.Device
import spock.lang.Specification;

class TestDevice extends Specification {

    def "test device toString method"() {
        given:
        Device device = new Device()
        device.setType("CONSOLE")
        device.setPlatform("XBOX")

        when:
        String temp = device.toString()

        then:
        temp == "CONSOLE:XBOX"
    }

    def "test device setType method"() {
        given:
        Device device = new Device()

        when:
        device.setType("CONSOLE")

        then:
        device.type == "CONSOLE"
    }

    def "test device setPlatform method"() {
        given:
        Device device = new Device()
        device.setType("CONSOLE")


        when:
        device.setPlatform("XBOX")

        then:
        device.platform == "XBOX"
    }

    def "test device getType method"() {
        given:
        Device device = new Device()


        when:
        device.setType("CONSOLE")

        then:
        device.getType() == "CONSOLE"
    }

    def "test device getPlatform method"() {
        given:
        Device device = new Device()


        when:
        device.setPlatform("XBOX")

        then:
        device.getPlatform() == "XBOX"
    }
}