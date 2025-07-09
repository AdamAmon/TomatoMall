package com.example.tomatomall.vo;

import com.example.tomatomall.po.Account;
import com.example.tomatomall.po.Coupon;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CouponVO {
    private int id;

    private int userId;

    private int discount;

    private int top;

    private long getTime;

    private Account account;

    public Coupon toPO(){
        Coupon coupon=new Coupon();
        coupon.setId(this.id);
        coupon.setDiscount(this.discount);
        coupon.setUserId(this.userId);
        coupon.setTop(this.top);
        coupon.setGetTime(this.getTime);
        coupon.setAccount(this.account);

        return coupon;
    }
}
