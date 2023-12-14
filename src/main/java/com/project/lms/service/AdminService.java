package com.project.lms.service;

import com.project.lms.entity.Admin;
import com.project.lms.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepo;

    public void createAdmin(Admin admin){
        adminRepo.save(admin);
    }

    public Admin findById(Integer adminId) {
        return  adminRepo.findById(adminId).orElse(null);
    }
}
