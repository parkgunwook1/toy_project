package com.toyproject.hello.dev.comment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class Comment {

    @Id
    public String commentId;

    public int postId;
    public String author;
    public String content;
    public Date createDate;
    public Date modifiedDate;
}
