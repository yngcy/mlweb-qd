package cn.edu.swust.qd.qdservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "hello Jenkins! --v3.0.0";
    }
}
