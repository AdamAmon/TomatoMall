package com.example.tomatomall.repository;

import com.example.tomatomall.po.Info;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfoRepository extends JpaRepository<Info,Integer> {
    List<Info> findAllByProductId(int productId);
}
