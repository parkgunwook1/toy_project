package com.toyproject.hello.dev.kakao.service;

import com.toyproject.hello.dev.user.dto.UserDto;
import com.toyproject.hello.dev.user.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface KakaoService {
    ResponseEntity<String> getKakaoToken(String code); // 카카오 토큰 발급.
    String getAccessToken(ResponseEntity<String> kakaoTokenResponse); // 카카오 응답 중 "accessToken" 만 추출하기.
    ResponseEntity<String> getUserProfile(String accessToken); // 카카오 유저 데이터 추출하기.
    UserDto kakaoLogin(ResponseEntity<String> kakaoUserProfileResponse); // 카카오 유저 데이터로 카카오 로그인 하기.
}
