package com.toyproject.hello.dev.user.repository;

import com.toyproject.hello.dev.user.dto.UserDto;
import com.toyproject.hello.dev.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    /**
     * findBy 뒤에 오는 필드 이름을 기준으로 쿼리를 자동으로 생성합니다.
     * 예를 들어, User 엔티티에 userId와 userPassword 필드가 있다고 가정하면,
     * 메서드 이름을 **findByUserIdAndUserPassword**로 작성해야 합니다.
     * **/
    Optional<User> findByUserIdAndUserPassword(String userId, String userPassword); // userId, userPassword를 통해 User객체 조회.
}
