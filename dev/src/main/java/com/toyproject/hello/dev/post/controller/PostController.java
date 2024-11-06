package com.toyproject.hello.dev.post.controller;

import com.toyproject.hello.dev.post.dto.PostDto;
import com.toyproject.hello.dev.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PostController {

    /* TODO LIST
    *
    * - 게시글 이미지
    * - ui 깨지는 거 수정, ui 편집
    * - 상세보기 -> 수정, 삭제
    * - 헤더, 푸터
    * - 게시글에 이미지
    *
    * - 수정하기 진행중..
    * */

    @Autowired
    private PostService postService;

    // 상세페이지
    @GetMapping("post/detail")
    public String detailPost(@RequestParam("postId") int postId, Model model) throws IllegalAccessException {
        PostDto post = postService.detailPostById(postId);
        model.addAttribute("post",post);
        return "post/detail";
    }

    // 게시글 수정
    @PostMapping("/post/update")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updatePost(@RequestBody PostDto postDto) {
        Map<String, Object> response = new HashMap<>();
        try {
            // PostDto 객체를 이용하여 게시물 업데이트
            PostDto updatedPost = postService.updatePost(postDto);

            response.put("success", true);
            response.put("title", updatedPost.getTitle());
            response.put("content", updatedPost.getContent());
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "수정 실패");
        }
        return ResponseEntity.ok(response);
    }

    // 게시글 삭제
    @PostMapping("/post/delete")
    public String deletePost(@RequestParam("postId") int postId) {
        postService.deletePostById(postId);
        return "redirect:/";
    }

    // 게시글 작성
    @PostMapping("/post/write")
    public String write(PostDto postDto) {
        postService.writePost(postDto);
        return "redirect:/";
    }

}


/* @RequestParam 과 @PathVariable 차이
 *
 * @RequestParam : URL 에서 클라이언트가 전송하는 파라미터를 받아오는 어노테이션
 * @PathVariable : URL 에서 경로 변수를 받아오는 어노테이션
 *
 * @RequestParam
 *       - 쿼리 파라미터를 통해 추가적인 정보를 전달할 때
 *       - 주로 GET 요청에서 사용
 *       - 예시 : /api/items?id=123&name=John
 *
 * @PathVariable
 *       - URL 경로에서 변수를 추출하는 데 사용
 *       - 주로 RESTFUL API 에서 사용
 *       - 예시 : /api/items/123
 *
 * Model 객체는 컨트롤러와 뷰 사이에서 데이터를 전달하는 데 사용된다.
 * */
