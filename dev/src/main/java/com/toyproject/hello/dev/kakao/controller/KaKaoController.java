package com.toyproject.hello.dev.kakao.controller;

import com.toyproject.hello.dev.kakao.service.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/kakao")
public class KaKaoController {
    @Autowired
    private KakaoService kakaoService;

    @GetMapping("login")
    @ResponseBody
    public String kakaoCallback(String code) {
        System.out.printf("카카오 인증 코드 : " + code + "\n");

        // 카카오토큰response 받기.
        ResponseEntity<String> kakaoTokenResponse = kakaoService.getKakaoToken(code);
        System.out.printf("카카오 토큰 : " + kakaoTokenResponse.getBody() + "\n");

        // 카카오response에서 accessToken(유저데이터를 추출하기 위한 토큰) 추출
        String accessToken = kakaoService.getAccessToken(kakaoTokenResponse);
        System.out.printf("kakaoTokenResponse.getBody()에서 뽑아온 accessToken 값 : " + accessToken + "\n");

        // accessToken으로 카카오유저프로필response 받기.
        ResponseEntity<String> kakaoUserProfileResponse = kakaoService.getUserProfile(accessToken);
        System.out.printf("kakaoUserProfileResponse 값 : " + kakaoUserProfileResponse.getBody() + "\n");

        // 카카오유저프로필response에서 일부 데이터를 user DB에 저장하고 로그인 처리.
        if(kakaoService.kakaoLogin(kakaoUserProfileResponse)) {
            System.out.printf("카카오 로그인 성공!");
        }
        else {
            System.out.printf("카카오 로그인 실패!");
        }

        return "kakaoUserProfileResponse 값 : " + kakaoUserProfileResponse.getBody();
    }
}
