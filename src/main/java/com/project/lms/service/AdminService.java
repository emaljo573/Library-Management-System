package com.project.lms.service;

import com.project.lms.entity.Admin;
import com.project.lms.entity.AuthUser;
import com.project.lms.repository.AdminRepository;
import com.project.lms.utils.AuthUserService;
import com.project.lms.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepo;

    @Autowired
    AuthUserService authUserService;


    public void createAdmin(Admin admin){
        AuthUser authUser=admin.getAuthUser();
        authUser=authUserService.saveAuthUser(authUser, Constants.ADMIN_USER);
        admin.setAuthUser(authUser);
        adminRepo.save(admin);
    }

    public Admin findById(Integer adminId) {
        return  adminRepo.findById(adminId).orElse(null);
    }
}
