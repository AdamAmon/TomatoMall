package com.example.tomatomall.vo;


import com.example.tomatomall.enums.RoleEnum;
import com.example.tomatomall.po.Account;
import com.example.tomatomall.po.Comment;
import com.example.tomatomall.po.Coupon;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AccountVO {

    private int id;

    private String username;

    private String name;

    private  String phone;

    private RoleEnum role;

    private String password;

    private String email;

    private String avatar;

    private String address;
    //account负责关联购物车

    private Integer vip;

    private Integer expe;

    private Integer recommendTicket;

    private List<CouponVO> couponVOs;

    private List<CommentVO> comments;

    private Integer moneyProgress;

    public Account toPO(){
        Account account=new Account();
        account.setId(this.id);
        account.setUsername(this.username);
        account.setName(this.name);
        account.setPhone(this.phone);
        account.setRole(this.role);
        account.setAvatar(this.avatar);
        account.setEmail(this.email);
        account.setPassword(this.password);
        account.setAddress(this.address);

        //cart

        account.setVip(this.vip);
        account.setExpe(this.expe);
        account.setRecommendTicket(this.recommendTicket);
        List<Coupon> coupons=new LinkedList<>();
        if(this.couponVOs!=null){
            for(int i=0;i<this.couponVOs.size();i++){
                coupons.add(this.couponVOs.get(i).toPO());
            }
        }
        account.setCoupons(coupons);
        account.setMoneyProgress(this.moneyProgress);
        List<Comment> comments=new LinkedList<>();
        if(this.comments!=null){
            for(int i=0;i<this.comments.size();i++){
                comments.add(this.comments.get(i).toPO());
            }
        }
        account.setComments(comments);

        return account;
    }
}
