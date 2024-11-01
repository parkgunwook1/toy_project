package com.toyproject.hello.dev.user.service;

import com.toyproject.hello.dev.user.dto.UserDto;
import com.toyproject.hello.dev.user.entity.User;
import com.toyproject.hello.dev.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean join(UserDto user) { // 회원가입
        User UserEntity = new User();

        // UserDto의 값들을 UserEntity에다 다 넣어줌.
        UserEntity.setUserId(user.getUserId());
        UserEntity.setUserPassword(user.getUserName());
        UserEntity.setUserName(user.getUserName());
        UserEntity.setUserAge(user.getUserAge());
        UserEntity.setUserGender(user.getUserGender());
        UserEntity.setUserEmail(user.getUserEmail());
        UserEntity.setUserZipcode(user.getUserZipcode());
        UserEntity.setUserAddress(user.getUserAddress());
        UserEntity.setUserAddressDetail(user.getUserAddressDetail());
        UserEntity.setUserType(user.getUserType());

        userRepository.save(UserEntity); // JpaRepository의 .seve(Entity)를 사용해 값을 저장.

        return true;
    }

    @Override
    public User checkId(String userId) { // 아이디 중복체크
        User checkUser = userRepository.findById(userId).orElse(null); //
        return checkUser;
    }
}
