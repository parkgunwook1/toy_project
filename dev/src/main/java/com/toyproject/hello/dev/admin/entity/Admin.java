package com.toyproject.hello.dev.admin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Admin {

    // Entity 어노테이션 있어야 맵핑된다.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminNo;

    private String adminId;
    private String adminPassword;

}
