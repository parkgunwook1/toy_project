package com.toyproject.hello.dev.kakao.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KakaoProfileDto {
    public Long id;
    public String connectedAt;
    public Properties properties;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Properties {
        public String nickname;
        public String profileImage;
        public String thumbnailImage;
    }

    public KakaoAccount kakaoAccount;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KakaoAccount {
        public Boolean profileNicknameNeedsAgreement;
        public Boolean profileImageNeedsAgreement;
        public Profile profile;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Profile {
            public String nickname;
            public String thumbnailImageUrl;
            public String profileImageUrl;
            public Boolean isDefaultImage;
            public Boolean isDefaultNickname;
        }
    }
}
