package com.example.tomatomall.po;

import com.example.tomatomall.vo.CartVO;
import com.example.tomatomall.vo.CartItemVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cart {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="cartId")
    private int cartId;

    @Basic
    @Column(name="userId")
    private int userId;

    public CartVO toVO(){
        CartVO cartVO=new CartVO();
        cartVO.setCartId(this.cartId);
        cartVO.setUserId(this.userId);

        return cartVO;
    }
}
