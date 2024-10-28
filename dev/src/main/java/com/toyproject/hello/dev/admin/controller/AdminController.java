package com.toyproject.hello.dev.admin.controller;

import com.toyproject.hello.dev.admin.dto.AdminDto;
import com.toyproject.hello.dev.admin.service.AdminService;
import com.toyproject.hello.dev.admin.entity.Admin;
import com.toyproject.hello.dev.post.entity.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // lombok 제공 : final 붙은 속성 생성자 자동으로 만들어준다.
@Controller
@Slf4j  // lombok 제공
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/admin/real")
    public String adminHome() {
        return "admin/adminReal";
    }

    @GetMapping("/admin")
    public String getAllAdmin() {
        return "admin/admin";
    }

    @GetMapping("/admin/{adminId}") // 테스트
    public Admin getAdminById(@PathVariable String adminId) {
        return adminService.getAdminId(adminId);
    }

    @GetMapping("/admin/password/{adminId}") // 테스트
    @ResponseBody
    public String getAdminPassword(@PathVariable String adminId) {

        AdminDto adminDto = adminService.getAdminPassword(adminId);

        if (adminDto != null) {
            return  adminDto.getAdminPassword();
        }
        return null;
    }

    // 회원 리스트 리턴
    @PostMapping("/admin/user")
    public List<User> getUserList() {

        return null;
    };

    // 게시글 랭킹 + 게시글 리스트 리턴
    @PostMapping("/admin/post")
    public List<Post> getPostList() {

        return null;
    };
}
