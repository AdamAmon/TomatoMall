package com.example.tomatomall.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/accounts/register")
                .excludePathPatterns("/api/accounts/login")
                .excludePathPatterns("/api/accounts/ticket")
                .excludePathPatterns("/api/accounts/comment")
                .excludePathPatterns("/api/accounts/comment/make")
                .excludePathPatterns("/api/accounts/comment/delete")
                .excludePathPatterns("/api/accounts/comment/update")
                .excludePathPatterns("/api/accounts/vip")
                .excludePathPatterns("/api/dashboard")
                .excludePathPatterns("/api/products")
                .excludePathPatterns("/api/products/stockpile")
                .excludePathPatterns("/api/cart/{cart_item_id}/quantity")
                .excludePathPatterns("/api/cart/{cart_item_id}")
                .excludePathPatterns("/api/cart")
                .excludePathPatterns("/api/cart/getAll")
                .excludePathPatterns("/api/orders/create")
                .excludePathPatterns("/api/orders/showOrder")
                .excludePathPatterns("/api/orders/pay")
                .excludePathPatterns("/api/orders/notify")
                .excludePathPatterns("/api/advertisements")
                .excludePathPatterns("/api/advertisements/single")
                .excludePathPatterns("/api/coupons")
                .excludePathPatterns("/api/coupons/all/{userId}")
                .excludePathPatterns("/api/coupons/{userId}")
                .excludePathPatterns("/api/coupons/use")
                .excludePathPatterns("/api/recommend")
                .order(1);
    }


}
