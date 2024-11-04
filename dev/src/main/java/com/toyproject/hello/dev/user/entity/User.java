package com.toyproject.hello.dev.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @Column(name = "user_id", length = 50, nullable = false)
    private String userId;

    @Column(name = "user_password", length = 300, nullable = false)
    private String userPassword;

    @Column(name = "user_email", length = 50, nullable = false)
    private String userEmail;

    @Column(name = "user_name", length = 50, nullable = false)
    private String userName;

    @Column(name = "user_age", nullable = false)
    private int userAge;

    @Column(name = "user_gender", length = 50, nullable = false)
    private String userGender;

    @Column(name = "user_zipcode", length = 50, nullable = false)
    private int userZipcode;

    @Column(name = "user_address", length = 50, nullable = false)
    private String userAddress;

    @Column(name = "user_addretc", length = 50, nullable = false)
    private String userAddretc;

    @Column(name = "user_address_detail", length = 50, nullable = false)
    private String userAddressDetail;

    @Column(name = "user_type", length = 50, nullable = true)
    private String userType;
}
