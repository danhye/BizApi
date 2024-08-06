package kr.co.daou.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class Controller {
    @RequestMapping(value = "/home")
    public String home(){
        return "index.html";
    }
}
