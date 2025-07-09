package com.example.tomatomall.po;

import com.example.tomatomall.enums.RoleEnum;
import com.example.tomatomall.vo.AccountVO;
import com.example.tomatomall.vo.CommentVO;
import com.example.tomatomall.vo.CouponVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Account {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id")
    private int id;

    @Basic
    @Column(name="username")
    private String username;

    @Basic
    @Column(name="password")
    private String password;

    @Basic
    @Column(name="name")
    private String name;

    /**
     * 头像
     */
    @Basic
    @Column(name="avatar")
    private String avatar;

    @Basic
    @Column(name="phone")
    private String phone;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "address")
    private String address;

    @Basic
    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Basic
    @Column(name = "vip")
    private Integer vip;

    /**
     * vip经验
     */
    @Basic
    @Column(name = "expe")
    private Integer expe;

    @Basic
    @Column(name = "recommendTicket")
    private Integer recommendTicket;


    @OneToMany(mappedBy = "account", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Coupon> coupons;


    @Basic
    @Column(name="moneyProgress")
    private Integer moneyProgress;


    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Comment> comments;
/*
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Orders> orders;
*/
    public AccountVO toVO(){
        AccountVO accountVO=new AccountVO();
        accountVO.setId(this.id);
        accountVO.setUsername(this.username);
        accountVO.setName(this.name);
        accountVO.setPhone(this.phone);
        accountVO.setRole(this.role);
        accountVO.setAvatar(this.avatar);
        accountVO.setEmail(this.email);
        accountVO.setPassword(this.password);
        accountVO.setAddress(this.address);

        //orders

        accountVO.setVip(this.vip);
        accountVO.setExpe(this.expe);
        accountVO.setRecommendTicket(this.recommendTicket);
        List<CouponVO> couponVOs=new LinkedList<>();
        if(this.coupons!=null){
            for(int i=0;i<this.coupons.size();i++){
                couponVOs.add(this.coupons.get(i).toVO());
            }
        }
        accountVO.setCouponVOs(couponVOs);
        accountVO.setMoneyProgress(this.moneyProgress);
        List<CommentVO> commentVOs=new LinkedList<>();
        if(this.comments!=null){
            for(int i=0;i<this.comments.size();i++){
                commentVOs.add(this.comments.get(i).toVO());
            }
        }

        accountVO.setComments(commentVOs);

        return accountVO;
    }
}
