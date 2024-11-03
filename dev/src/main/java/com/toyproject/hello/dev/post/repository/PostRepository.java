package com.toyproject.hello.dev.post.repository;

import com.toyproject.hello.dev.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {


}
