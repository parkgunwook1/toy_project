package com.toyproject.hello.dev.post.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int postId;

    @Column(nullable = false, length = 50)
    public String title;

    @Column(nullable = false, length = 300)
    public String content;

    @Column(nullable = false)
    public String author;

    public Date createDate;
    public Date modifiedDate;
    public int viewCount;
    public String image;
    public String comments;
}
