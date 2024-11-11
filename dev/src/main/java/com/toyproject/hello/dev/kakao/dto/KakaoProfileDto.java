package com.toyproject.hello.dev.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KakaoProfileDto {
    public Long id;

    @JsonProperty("connected_at")
    public String connectedAt;

    public Properties properties;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Properties {
        public String nickname;

        @JsonProperty("profile_image")
        public String profileImage;

        @JsonProperty("thumbnail_image")
        public String thumbnailImage;
    }

    @JsonProperty("kakao_account")
    public KakaoAccount kakaoAccount;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KakaoAccount {
        @JsonProperty("profile_nickname_needs_agreement")
        public Boolean profileNicknameNeedsAgreement;

        @JsonProperty("profile_image_needs_agreement")
        public Boolean profileImageNeedsAgreement;

        public Profile profile;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Profile {
            public String nickname;

            @JsonProperty("thumbnail_image_url")
            public String thumbnailImageUrl;

            @JsonProperty("profile_image_url")
            public String profileImageUrl;

            @JsonProperty("is_default_image")
            public Boolean isDefaultImage;

            @JsonProperty("is_default_nickname")
            public Boolean isDefaultNickname;
        }
    }
}
