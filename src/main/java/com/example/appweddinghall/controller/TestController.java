package com.example.appweddinghall.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/open")
    public String openWay() {
        return "openWay";
    }

    @GetMapping("/close")
    public String closeWay() {
        return "closeWay";
    }



}
