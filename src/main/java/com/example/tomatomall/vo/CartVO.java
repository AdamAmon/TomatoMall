package com.example.tomatomall.vo;

import com.example.tomatomall.po.Cart;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CartVO {
    private int cartId;

    private int userId;


    public Cart toPO(){
        Cart cart=new Cart();
        cart.setCartId(this.cartId);
        cart.setUserId(this.userId);

        return cart;
    }
}
