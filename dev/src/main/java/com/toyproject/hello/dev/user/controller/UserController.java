package com.toyproject.hello.dev.user.controller;

import com.toyproject.hello.dev.user.dto.UserDto;
import com.toyproject.hello.dev.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("login") // 로그인 페이지로 이동.
    public String loginPage(HttpSession session) {
        if(session.getAttribute("loginUser") != null) {
            return "redirect:/"; // 이미 로그인이 된 경우 index.jsp로 이동.
        }
        else {
            return "user/login"; // 아니면 '로그인 페이지' 로 이동.
        }
    }
    @PostMapping("login")// 로그인 진행
    public String login(UserDto user, HttpServletRequest req, Model model){
        HttpSession session = req.getSession();

        String userId = user.getUserId();
        String userPassword = user.getUserPassword();

        if(userService.login(userId, userPassword)) {
            System.out.printf(userId+"로 로그인 성공.\n");
            session.setAttribute("loginUser", userId);
            return "redirect:/";
        }
        else {
            System.out.printf("로그인 실패.\n");
            model.addAttribute("loginFailed", "로그인실패");
            return "user/login";
        }
    }

    @GetMapping("join") // 회원가입 페이지로 이동.
    public String joinPage() {
        return "user/join";
    }
    @PostMapping("join") // 회원가입 진행
    public String join(UserDto user) {
        System.out.println("UserDto 값: " + user);

        if(userService.join(user)) { // boolean 값으로 회원가입 체크
            System.out.println("회원가입 완료.");
            return "user/login";
        }
        else {
            System.out.printf("회원가입 실패");
            return "user/join";
        }
    }
    @GetMapping("checkId") // 회원가입 시 아이디 중복 체크
    @ResponseBody
    public String checkId(String userId) {
        System.out.printf("userId값 : "+userId);
        if(userService.checkId(userId) == null){
            System.out.printf("사용 가능한 아이디입니다.");
            return "O";
        }
        else {
            System.out.printf("중복된 아이디 입니다.");
            return "X";
        }
    }

    @GetMapping("logout") // 로그아웃 진행
    public String logout(HttpServletRequest req) {
        System.out.printf("로그아웃됨\n");
        req.getSession().invalidate();
        return "redirect:/";
    }
}
