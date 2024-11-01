package com.toyproject.hello.dev.user.service;

import com.toyproject.hello.dev.user.dto.UserDto;
import com.toyproject.hello.dev.user.entity.User;

public interface UserService {
    boolean join(UserDto user); // 회원가입
    User checkId(String userId); // 아이디 중복체크
}
