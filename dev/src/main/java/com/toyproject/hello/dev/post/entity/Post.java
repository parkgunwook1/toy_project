package com.toyproject.hello.dev.post.entity;

import com.toyproject.hello.dev.comment.entity.Comment;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "POST")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    public int postId;

    @Column(nullable = false, length = 50)
    public String title;

    @Column(nullable = false, length = 300)
    public String content;

    @Column(nullable = false)
    public String author;

    @Column(name = "create_date")
    public Date createDate;

    @Column(name = "modified_date")
    public Date modifiedDate;

    @Column(name = "view_count")
    public int viewCount;
    public String image;

    // 댓글과의 일대다 관계
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Comment> comments;
}
