package com.example.tomatomall.vo;


import com.example.tomatomall.po.Cart;
import com.example.tomatomall.po.CartItem;
import com.example.tomatomall.po.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
public class CartItemVO {
    private int itemId;

    private int productId;

    private int quantity;

    private int cartId;

    public CartItem toPO(){
        CartItem cartItem=new CartItem();
        cartItem.setItemId(this.itemId);
        cartItem.setProductId(this.productId);
        cartItem.setQuantity(this.quantity);
        cartItem.setCartId(this.cartId);
        return cartItem;
    }
}
