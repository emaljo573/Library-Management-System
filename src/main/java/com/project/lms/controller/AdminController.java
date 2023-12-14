package com.project.lms.controller;

import com.project.lms.dto.CreateAdminReq;
import com.project.lms.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;
    @PostMapping("/admin")
    public void createAdmin(@RequestBody CreateAdminReq req){
        adminService.createAdmin(req.toAdmin());
    }

}
