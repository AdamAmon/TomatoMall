package com.example.tomatomall.service;

import com.example.tomatomall.vo.CouponVO;

import java.util.List;

public interface CouponService {
    //获取优惠券
    CouponVO getFreeCoupon(int userId);

    //获取已有的所有优惠券
    List<CouponVO> getAllCoupons(int userId);

    //使用优惠券
    Boolean useCoupon(int id);
}
