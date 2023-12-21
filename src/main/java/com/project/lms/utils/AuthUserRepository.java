package com.project.lms.utils;

import com.project.lms.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUser,Integer> {

    AuthUser findByUserName(String userName);
}
