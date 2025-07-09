package com.example.tomatomall.repository;

import com.example.tomatomall.po.Stockpile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockpileRepository extends JpaRepository<Stockpile,Integer>{
    Stockpile findByProductId(int productId);
}
