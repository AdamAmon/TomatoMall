package com.example.tomatomall.po;

import com.example.tomatomall.vo.CouponVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Coupon {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id")
    private int id;

    @Basic
    @Column(name="userId")
    private int userId;

    @Basic
    @Column(name = "discount")
    private int discount;

    @Basic
    @Column(name = "top")
    private int top;

    @Basic
    @Column(name = "getTime")
    private long getTime;

    @ManyToOne
    @JoinColumn(name="account")
    private Account account;

    public CouponVO toVO(){
        CouponVO couponVO=new CouponVO();
        couponVO.setId(this.id);
        couponVO.setDiscount(this.discount);
        couponVO.setUserId(this.userId);
        couponVO.setTop(this.top);
        couponVO.setGetTime(this.getTime);
        couponVO.setAccount(this.account);

        return couponVO;
    }
}
