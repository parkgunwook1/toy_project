package com.toyproject.hello.dev.user.service;

import com.toyproject.hello.dev.user.dto.UserDto;
import com.toyproject.hello.dev.user.entity.User;
import com.toyproject.hello.dev.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 로그인 처리
    @Override
    public boolean login(String userId, String userPassword) {
        User UserEntity = new User();
        UserEntity.setUserId(userId);
        UserEntity.setUserPassword(userPassword);

        // 가입된 아이디 비밀번호 인지 확인.
        Optional<User> ckeckUser = userRepository.findByUserIdAndUserPassword(userId, userPassword);

        if (ckeckUser.isPresent()) { // .isPresent()를 사용하면 Optional객체의 유무를 확인할 수 있다.
            return true;
        } else {
            return false;
        }
    }

    // 회원가입 처리
    @Override
    public boolean join(UserDto user) {
        userRepository.save(User.from(user));
        return true;
    }

    // 아이디 중복체크
    @Override
    public User checkId(String userId) {
        User checkUser = userRepository.findById(userId).orElse(null);
        return checkUser;
    }
}
