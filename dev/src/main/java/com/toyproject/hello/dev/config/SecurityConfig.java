//package com.toyproject.hello.dev.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/", "/user/login", "/user/join", "/user/logout", "/user/checkId").permitAll() // 인증 필요 없는 경로 설정
//                        .requestMatchers("/admin").hasRole("ADMIN")
//                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
//                        .anyRequest().authenticated() // 그 외 모든 요청은 인증 필요
//                );
//
//        return http.build();
//    }
//}
