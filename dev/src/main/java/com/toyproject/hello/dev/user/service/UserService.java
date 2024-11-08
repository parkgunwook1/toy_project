package com.toyproject.hello.dev.user.service;

import com.toyproject.hello.dev.user.dto.UserDto;
import com.toyproject.hello.dev.user.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    boolean login(String userId, String userPassword); // 로그인 처리
    boolean join(UserDto user); // 회원가입 처리
    User checkId(String userId); // 아이디 중복체크
}
