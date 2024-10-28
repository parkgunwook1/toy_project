package com.toyproject.hello.dev.message.entity;


import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int messageId;

//    public int chat; -> chat 클래스 만들면 jpa 조인 @JoinColumn 참고

    @Column(length = 50)
    public String sender;

    @Column(length = 300)
    public String content;

    public Date timestamp;
    @Column(name = "is_read" , length = 50)
    public String isRead;
    @Column(name = "message_type",length = 50)
    public String messageType;
    @Column(length = 300)
    public String attachments;
}
