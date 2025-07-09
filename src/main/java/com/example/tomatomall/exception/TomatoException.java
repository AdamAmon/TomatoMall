package com.example.tomatomall.exception;

/**
 * 异常状态
 */
public class TomatoException extends RuntimeException {
    public TomatoException(String message) {
        super(message);
    }

    public static TomatoException usernameAlreadyExits(){
        return new TomatoException("用户名已存在");
    };

    public static TomatoException UsernameOrPasswordError(){
        return new TomatoException("用户不存在/用户密码错误");
    };

    public static TomatoException noLogin(){
        return new TomatoException("未登录");
    };

    public static TomatoException noProduct(){
        return new TomatoException("无商品");
    };

    public static TomatoException NumInListBelowZero() {
        return new TomatoException("商品不能为负值");
    }

    //public static TomatoException RechargeBelowZero() {return new TomatoException("充值额度小于等于零");}

    public static TomatoException OutOfStock() {
        return new TomatoException("缺货");
    }

    public static TomatoException fail() {
        return new TomatoException("获取购物车失败");
    }

    //public static TomatoException OrderNotPaid() {return new TomatoException("已有的订单未支付");}

    public static TomatoException NoSuchAdv() {
        return new TomatoException("广告不存在");
    }

    public static TomatoException TooManyAdvs() {
        return new TomatoException("广告数过多");
    }

    public static TomatoException NoCoupon(){
        return new TomatoException("没有优惠券");
    }

    public static TomatoException NoUserOrProduct(){
        return new TomatoException("无用户或商品");
    }

    public static TomatoException NoRecommendTicket(){
        return new TomatoException("无推荐票");
    }
}
