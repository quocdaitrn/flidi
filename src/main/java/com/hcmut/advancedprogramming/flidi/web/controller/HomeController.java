package com.hcmut.advancedprogramming.flidi.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
    @Value("${spring.application.name}")
    private String appName;

    @GetMapping
    public String index() {
        return String.format("Welcome to %s", appName);
    }
}
