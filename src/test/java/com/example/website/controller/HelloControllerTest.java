package com.example.website.controller;

import com.example.website.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class) // 테스트 진행 중 JUnit 내장 실행자 외의 다른 실행자 실행
@WebMvcTest(controllers = HelloController.class, // 웹 집중 어노테이션
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
)
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈 주입
    private MockMvc mvc; // 웹 API 테스트

    @Test
    @WithMockUser
    public void hello_Test() throws Exception {
        String hello = "hello Spring Boot!";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) // HTTP 헤더 상태코드
                .andExpect(content().string(hello)); // mvc.perform 결과 검증
    }

    @Test
    @WithMockUser
    public void helloDto_Test() throws Exception {
        String name = "test1";
        String nickname = "test2";

        mvc.perform(
                        get("/hello/dto")
                                .param("name", name)
                                .param("nickname", nickname))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.nickname", is(nickname)));
    }
}