package com.toyproject.hello.dev.kakao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toyproject.hello.dev.kakao.dto.KakaoProfileDto;
import com.toyproject.hello.dev.kakao.dto.OAuthTokenDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoServiceImpl implements KakaoService{

    // 카카오 토큰 발급.
    @Override
    public ResponseEntity<String> getKakaoToken(String code) { // POST방식으로 key=value 형식으로 데이터를 요청
        RestTemplate rt = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "2a15de94fea60ae830427c67bdfd6bae");
        params.add("redirect_uri", "http://localhost:8080/kakao/login");
        params.add("code", code);

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        // Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );
        return response;
    }

    // accessToken만 추출하기
    @Override
    public String getAccessToken(ResponseEntity<String> kakaoTokenResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthTokenDto oauthToken = null; // 한번 초기화

        try { // 파싱할 때 데이터의 키값이 틀릴 때를 대비.
            oauthToken = objectMapper.readValue(kakaoTokenResponse.getBody(), OAuthTokenDto.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String accessToken = oauthToken.getAccess_token(); // accessToken값만 추출.
        return accessToken;
    }

    // 카카오 유저 데이터 추출하기.
    @Override
    public ResponseEntity<String> getUserProfile(String accessToken) {
        RestTemplate rt = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

        // Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );
        return response;
    }

    // 카카오 유저 데이터로 카카오 로그인 하기.
    @Override
    public boolean kakaoLogin(ResponseEntity<String> kakaoUserProfileResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        KakaoProfileDto kakaoProfileDto = null; // 한번 초기화

        try { // 파싱할 때 데이터의 키값이 틀릴 때를 대비.
            kakaoProfileDto = objectMapper.readValue(kakaoUserProfileResponse.getBody(), KakaoProfileDto.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return false;
    }
}
