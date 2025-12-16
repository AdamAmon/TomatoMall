package com.example.tomatomall.service;

import com.example.tomatomall.vo.ProductVO;

import java.util.List;

public interface RecommendService {
    //每日推荐（其实是排行榜）
    List<ProductVO> getEverydayRecommend();
}
