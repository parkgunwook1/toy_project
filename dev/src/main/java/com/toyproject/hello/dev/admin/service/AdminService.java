package com.toyproject.hello.dev.admin.service;

import com.toyproject.hello.dev.admin.dto.AdminDto;
import com.toyproject.hello.dev.admin.repository.AdminRepository;
import com.toyproject.hello.dev.admin.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminId(String adminId) {
        return adminRepository.findById(adminId).orElse(null);
    }

    public AdminDto getAdminPassword(String adminId) {
        return adminRepository.findByAdminId(adminId)
                .map(admin -> new AdminDto(admin.getAdminId(), admin.getAdminPassword()))
                .orElse(null);
    }
}
