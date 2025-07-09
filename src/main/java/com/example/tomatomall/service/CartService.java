package com.example.tomatomall.service;

import com.example.tomatomall.vo.CartItemVO;
import com.example.tomatomall.vo.CartVO;

import java.util.List;
public interface CartService {
    //将商品加入购物车
    CartVO getProduct(int userId, String productId, int quantity);

    //删除购物车中的某个商品
    Boolean deleteCartProduct(int userId, String cart_item_id);

    //购物车的商品数量
    Boolean changeCartItemQuantity(int userId, String cart_item_id, int quantity);

    //获取购物车id与相应的用户id
    CartVO getCart(int userId);

    //获取购物车中的所有商品
    List<CartItemVO> getAllItems(int cartId);
}
