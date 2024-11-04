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
    public boolean login(String userId, String userPassword) { // 로그인 처리
        System.out.printf("UserServiceImpl에서 userId, userPw : " + userId + userPassword+"\n");

        User UserEntity = new User();
        UserEntity.setUserId(userId);
        UserEntity.setUserPassword(userId);

        Optional<User> ckeckUser = userRepository.findByUserIdAndUserPassword(userId, userPassword);

        System.out.printf("checkUser : "+ckeckUser+"\n");

        if (ckeckUser.isPresent()) { // .isPresent()를 사용하면 Optional객체의 유무를 확인할 수 있다.
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean join(UserDto user) { // 회원가입 처리
        User UserEntity = new User();
        UserEntity.setUserId(user.getUserId()); // UserDto의 값들을 UserEntity에다 다 넣어줌.
        UserEntity.setUserPassword(user.getUserPassword());
        UserEntity.setUserName(user.getUserName());
        UserEntity.setUserAge(user.getUserAge());
        UserEntity.setUserGender(user.getUserGender());
        UserEntity.setUserEmail(user.getUserEmail());
        UserEntity.setUserZipcode(user.getUserZipcode());
        UserEntity.setUserAddress(user.getUserAddress());
        UserEntity.setUserAddretc(user.getUserAddretc());
        UserEntity.setUserAddressDetail(user.getUserAddressDetail());
        UserEntity.setUserType(user.getUserType());

        userRepository.save(UserEntity); // JpaRepository의 .seve(Entity)를 사용해 값을 저장.

        return true;
    }

    @Override
    public User checkId(String userId) { // 아이디 중복체크
        User checkUser = userRepository.findById(userId).orElse(null);
        return checkUser;
    }
}
