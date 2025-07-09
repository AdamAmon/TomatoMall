package com.example.tomatomall.repository;

import com.example.tomatomall.po.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{
    List<Orders> findByUserId(int userId);
    Orders findById(int orderId);
    List<Orders> findAllByUserId(int userId);
}
