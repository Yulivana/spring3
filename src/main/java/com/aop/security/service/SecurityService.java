package com.aop.security.service;

import com.aop.security.model.User;

public class SecurityService {

    public boolean checkRight(User user) {
        return user.getRole().equalsIgnoreCase("admin");
    }
}
