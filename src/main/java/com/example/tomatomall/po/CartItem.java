package com.example.tomatomall.po;

import com.example.tomatomall.vo.CartItemVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CartItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="itemId")
    private int itemId;

    @Basic
    @Column(name="productId")
    private int productId;

    @Basic
    @Column(name="quantity")
    private int quantity;

    @Basic
    @Column(name="cartId")
    private int cartId;

    public CartItemVO toVO(){
        CartItemVO cartItemVO=new CartItemVO();
        cartItemVO.setItemId(this.itemId);
        cartItemVO.setProductId(this.productId);
        cartItemVO.setQuantity(this.quantity);
        cartItemVO.setCartId(this.cartId);
        return cartItemVO;
    }
}
