package com.toyproject.hello.dev.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {

    private String adminId;
    private String adminPassword;

    /* DTO(Data Transfer Object)
    *
    * Dto 말 그대로 해석하면 데이터 전송 객체이다.
    * 즉, 데이터의 전송을 담당하는 클래스이다.
    *
    * 웹 서비스의 클라이언트와 서버, 더 자세히는 클라이언트와 서버의 서비스 계층 사이에서 교환되는 데이터를 담는 그릇이다.
    *
    * Dto 클래스는 클라이언트의 Request_body에 있는 Entity의 필드들을 담아 다음 로직을 처리하는 곳으로 데이터를 넘겨준다.
    * 반대로 서버 쪽에서 클라이언트로 Response을 보낼 때도 이럴 Dto 클래스를 이용하면 된다.
    *
    * 사용하는 이유는?
    *  -> Entity 클래스는 데이터베이스와 맞닿은 핵심 클래스이다. 클라이언트 쪽의 변경은 빈번히 일어날 수 있는데 그에 따라 데이터베이스의
    *     스키마가 변경되는 것은 매우 큰 비용이라는 것이다.
    *  -> srp 단일책임의 원칙도 위배되는 행위다.
    * */


}
