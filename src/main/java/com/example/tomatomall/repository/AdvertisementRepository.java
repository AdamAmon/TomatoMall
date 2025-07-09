package com.example.tomatomall.repository;

import com.example.tomatomall.po.Advertisement;
import com.example.tomatomall.po.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface AdvertisementRepository  extends JpaRepository<Advertisement,Integer>{
    Advertisement findByAdvId(int advId);
    List<Advertisement> findAllByProductId(int productId);
}
