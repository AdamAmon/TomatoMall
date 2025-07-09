package com.example.tomatomall.configure;

import com.example.tomatomall.exception.TomatoException;
import com.example.tomatomall.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    TokenUtil tokenUtil;

    /**
     * 拦截器
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        String uri = request.getRequestURI();
        String method = request.getMethod();
        if ("/api/accounts".equals(uri) && "POST".equalsIgnoreCase(method)) {
            return true;
        }
        /*
        * 验证token 与 拦截错误
        */
        String token = request.getHeader("token");
        if(token!=null&&tokenUtil.verifyToken(token)){
            request.getSession().setAttribute("currentAccount",tokenUtil.getAccount(token));
            return true;
        }
        else {
            throw TomatoException.noLogin();
        }
    }
}
