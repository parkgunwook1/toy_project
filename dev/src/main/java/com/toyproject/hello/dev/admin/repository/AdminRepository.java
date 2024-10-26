package com.toyproject.hello.dev.admin.repository;

import com.toyproject.hello.dev.admin.vo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    Optional<Admin> findByAdminId(String adminId);
}
