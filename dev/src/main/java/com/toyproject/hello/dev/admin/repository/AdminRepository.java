package com.toyproject.hello.dev.admin.repository;

import com.toyproject.hello.dev.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
*
 * Spring Data JPA
 * JpaRepository -> JPA(Java Persistence API)를 사용하여 데이터베이스의 CRUD 작업을 간편하게 수행하도록 돕는 인터페이스다.
 *
 * JpaRepository<Admin,String> : JpaRepository 두 개의 제네릭 타입을 받는다.
 *  - 첫 번째 Admin 데이터베이스와 맵핑된 엔티티 클래스이다. 즉, 데이터베이스에서 관리할 테이블을 나타낸다.
 *  - 두 번째 String 해당 엔티티의 기본 키의 자료형이다.
 *
 * Admin 엔티티 클래스 : Admin 클래스는 데이터베이스 테이블과 매핑된 엔티티로, Spring Data JPA AdminRepository를 통해 클래스의 인스턴스를
 *                     데이터베이스와 매핑된 상태에서 사용할 수 있도록 한다. 엔티티 클래스는 필드와 @Entity와 같은 JPA 어노테이션을 가지고 있어야한다.
* */
@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    Optional<Admin> findByAdminId(String adminId);
}
