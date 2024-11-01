package com.toyproject.hello.dev.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString // Controller에서 DTO값을 print문으로 확인할 때 JSON과 비슷한 형식으로 출력할 수 있게 해준다.
public class UserDto {
    private String userId;
    private String userPassword;
    private String userName;
    private int userAge;
    private String userGender;
    private String userEmail;
    private int userZipcode;
    private String userAddress;
    private String userAddressDetail;
    private String userType;
}
