package com.example.website.config;

import com.example.website.config.auth.PrincipalDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // 스프링 시큐리티 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PrincipalDetailService principalDetailService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // csrf 토큰 해제
                .authorizeRequests() // URL별 권한 관리 설정
                .antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**").permitAll() // 권한 관리대상 지정
                .anyRequest().authenticated()
                .and()
                .formLogin() // 권한이 없는 사람이 접근 시 로그인 페이지로 이동
                .loginPage("/auth/user/login") // 로그인 페이지 URL
                .loginProcessingUrl("/auth/user/login") // 스프링 시큐리티가 해당 주소로 요청이 되는 로그인을 가로채 대신 로그인
                .defaultSuccessUrl("/"); // 로그인 성공 시 해당 URL로 이동

        http
                .rememberMe().tokenValiditySeconds(60 * 60 * 7) // 쿠키를 7일동안 유지
                .userDetailsService(principalDetailService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}