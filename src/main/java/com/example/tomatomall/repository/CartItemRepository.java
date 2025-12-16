package com.example.tomatomall.repository;

import com.example.tomatomall.po.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
    CartItem findByItemId(int id);
    List<CartItem> findAllByProductId(int productId);
    List<CartItem> findAllByCartId(int cartId);
}
