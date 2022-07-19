package com.example.website.controller;

import com.example.website.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON 반환
public class HelloController {

    @GetMapping("/hello") // HTTP 메소드인 GET 요청을 받을 수 있음
    public String hello() {
        return "hello Spring Boot!";
    }
    @GetMapping("/hello/dto")
    public HelloResponseDto helloResponseDto(@RequestParam("name") String name, @RequestParam("nickname") String nickname) {
        return new HelloResponseDto(name, nickname);
    }
}