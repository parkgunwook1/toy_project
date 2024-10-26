package com.toyproject.hello.dev.admin.controller;

import com.toyproject.hello.dev.admin.service.AdminService;
import com.toyproject.hello.dev.admin.vo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public String getAllAdmin() {
        return "admin";
    }

    @GetMapping("/admin/{adminId}") // Admin ID를 사용하는 경로
    public Admin getAdminById(@PathVariable String adminId) {
        return adminService.getAdminId(adminId);
    }

    @GetMapping("/admin/password/{adminId}") // 비밀번호를 가져오는 경로
    @ResponseBody
    public String getAdminPassword(@PathVariable String adminId) {

        String password = adminService.getAdminPassword(adminId);

        System.out.println(password);

        return password;
    }
}
