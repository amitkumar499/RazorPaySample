package com.practice.RazorPaySample.controller;
/* 
Created by amit.chaurasia 
on 7/26/20 
*/


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/home")
    public String home() {
        return "index";
    }

    @RequestMapping("/")
    public String root() {
        return "index";
    }
}