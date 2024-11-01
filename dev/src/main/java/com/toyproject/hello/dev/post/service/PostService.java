package com.toyproject.hello.dev.post.service;

import com.toyproject.hello.dev.post.dto.PostDto;
import com.toyproject.hello.dev.post.entity.Post;
import com.toyproject.hello.dev.post.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;



import java.util.List;


@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> getPostList() {
        List<Post> posts = postRepository.findAll();
        System.out.println("test");
        System.out.println(posts.get(0));
        return posts.stream()
                .filter(post -> "Y".equals(post.getStatus()))
                .map(PostDto::toPostDto)
                .collect(Collectors.toList());
    }

    public PostDto detailPostById(int postId) throws IllegalAccessException {

        if(postId <= 0) {
            throw new IllegalAccessException("게시글 ID가 필요합니다.");
        }

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        if("N".equals(post.getStatus())) {
            throw new IllegalAccessException("삭제된 게시글입니다.");
        }
        updateViewCount(post);
        postRepository.save(post);
        return PostDto.toPostDto(post);
    }

    public static void updateViewCount(Post post) {
        post.setViewCount(post.getViewCount() + 1);
    }

    public PostDto editPostById(PostDto postDto) throws IllegalAccessException{

        if (postDto == null) {
            throw new IllegalAccessException("postId를 인자로 넘겨줘야 합니다.");
        }
        int postId = postDto.getPostId();

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다. id = " + postId));

        return PostDto.toPostDto(post);
    }

    public void deletePostById(int postId) {

        Post post = postRepository.findById(postId)
                        .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다. id" + postId));
        post.setStatus("N");
        postRepository.save(post);
    }
    // 게시글 작성
    public void writePost(PostDto postDto) {
        postRepository.save(Post.ToPostEntity(postDto));
    }
}
