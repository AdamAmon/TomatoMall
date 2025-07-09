package com.example.tomatomall.repository;

import com.example.tomatomall.po.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon,Integer> {
    List<Coupon> findAllByUserId(int userId);
    Coupon findById(int id);
}
