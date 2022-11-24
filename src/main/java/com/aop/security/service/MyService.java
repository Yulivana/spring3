package com.aop.security.service;

import com.aop.security.SecurityAnnotation;
import com.aop.security.model.Balance;
import com.aop.security.model.User;


public class MyService {

    @SecurityAnnotation
    public Balance getAccountBalance(User user) {
        Balance balance = new Balance();
        balance.setBalance("Balance for User "+ user.getName());
        return balance;
    }

}
