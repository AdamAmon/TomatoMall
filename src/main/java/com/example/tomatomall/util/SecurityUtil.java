package com.example.tomatomall.util;

import com.example.tomatomall.po.Account;
import com.example.tomatomall.po.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

@Component
public class SecurityUtil {

    @Autowired
    HttpServletRequest httpServletRequest;

    public Account getCurrentAccount(){
        return (Account) httpServletRequest.getSession().getAttribute("currentAccount");
    }

    public Product getCurrentProduct(){
        return (Product) httpServletRequest.getSession().getAttribute("currentProduct");
    }
}
