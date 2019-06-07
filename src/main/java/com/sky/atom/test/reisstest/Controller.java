package com.sky.atom.test.reisstest;

import org.springframework.web.bind.annotation.*;


@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/add")
    public String add() {
        return "add";
    }

    @GetMapping("/del")
    public String rem() {
        return "rem";
    }

    @GetMapping("/")
    public String greeting() {
        return "index";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/delError")
    public String delError() {
        return "delError";
    }

    @GetMapping("/delSuccess")
    public String delSuccess() {
        return "delSuccess";
    }

    @GetMapping("/addError")
    public String addError() {
        return "addError";
    }

    @GetMapping("/addSuccess")
    public String addSuccess() {
        return "addSuccess";
    }

//        StringBuilder builder = new StringBuilder();
//        for (Device device : devices) {
//            builder.append(device.getType() + " " + device.getPlatform() + "<br>");
//        }
//        return builder.toString();

}

