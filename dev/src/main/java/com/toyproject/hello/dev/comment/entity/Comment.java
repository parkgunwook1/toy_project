package com.toyproject.hello.dev.comment.entity;

import com.toyproject.hello.dev.post.entity.Post;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    public int commentId;

    @ManyToOne // 다대 일 관계 -> 여러개의 Comment가 하나의 Post에 속할 수 있다는 의미
    @JoinColumn(name = "post_id", nullable = false) // 외래키 설정
    public Post post;
    public String author;
    public String content;

    @Column(name = "create_date")
    public Date createDate;

    @Column(name = "modified_date")
    public Date modifiedDate;
}
