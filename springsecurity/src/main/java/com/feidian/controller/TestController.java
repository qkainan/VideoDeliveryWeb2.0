package com.feidian.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/Hello")
    @PreAuthorize("hasAuthority('test')")
    public String Hello(){
        return "Hello";
    }
}
