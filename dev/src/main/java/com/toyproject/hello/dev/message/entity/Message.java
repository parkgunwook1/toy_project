package com.toyproject.hello.dev.message.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class Message {

    @Id
    public int messageId;

    public int chatRoomId;

    public String sender;
    public String content;
    public Date timestamp;
    public String isRead;
    public String messageType;
    public String attachments;
}
