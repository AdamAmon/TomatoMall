package com.example.tomatomall.service.serviceImpl;

import com.example.tomatomall.exception.TomatoException;
import com.example.tomatomall.po.Coupon;
import com.example.tomatomall.repository.AccountRepository;
import com.example.tomatomall.repository.CouponRepository;
import com.example.tomatomall.service.CouponService;
import com.example.tomatomall.vo.CouponVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {
    private static final long TIME = 1000*60*60*24;
    @Autowired
    CouponRepository couponRepository;

    @Autowired
    AccountRepository accountRepository;
    @Override
    public CouponVO getFreeCoupon(int userId){
        Coupon coupon=new Coupon();
        coupon.setUserId(userId);
        coupon.setTop(100);
        coupon.setDiscount(20);
        coupon.setGetTime( System.currentTimeMillis());
        Coupon newcoupon=couponRepository.save(coupon);
        return newcoupon == null ? null : newcoupon.toVO();
    }

    @Override
    public List<CouponVO> getAllCoupons(int userId){
        List<CouponVO> couponVOS=new LinkedList<>();
        List<Coupon> coupons=couponRepository.findAllByUserId(userId);
        List<Coupon> remov=new LinkedList<>();
        long time=0;
        //在获取的同时检测是否过期
        for(int i=0;i<coupons.size();i++){
            time=System.currentTimeMillis()-coupons.get(i).getGetTime();
            if(time<TIME){
                couponVOS.add(coupons.get(i).toVO());
            }
            else {
                remov.add(coupons.get(i));
            }
        }
        for(int i=0;i<remov.size();i++){
            couponRepository.delete(remov.get(i));
        }
        return couponVOS;
    }

    @Override
    public Boolean useCoupon(int id){
        Coupon coupon=couponRepository.findById(id);
        if(coupon==null){
            throw TomatoException.NoCoupon();
        }
        couponRepository.delete(coupon);
        return true;
    }
}
