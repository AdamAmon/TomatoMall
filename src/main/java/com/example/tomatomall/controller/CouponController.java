package com.example.tomatomall.controller;

import com.example.tomatomall.service.CouponService;
import com.example.tomatomall.vo.CouponVO;
import com.example.tomatomall.vo.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    @Resource
    CouponService couponService;

    /**
     * 获取优惠券
     */
    @PostMapping("/{userId}")
    public Response<CouponVO> getFreeCoupon(@PathVariable("userId") int userId){
        return Response.buildSuccess(couponService.getFreeCoupon(userId));
    }

    /**
     * 所有优惠券
     */
    @GetMapping("/all/{userId}")
    public Response<List<CouponVO>> getAllCoupon(@PathVariable("userId") int userId){
        return Response.buildSuccess(couponService.getAllCoupons(userId));
    }

    /**
     * 使用优惠券
     */
    @PostMapping("/use")
    public Response<Boolean> useCoupon(@RequestParam("id") int id){
        return Response.buildSuccess(couponService.useCoupon(id));
    }
}
