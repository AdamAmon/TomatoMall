package com.example.tomatomall.controller;

import com.example.tomatomall.service.RecommendService;
import com.example.tomatomall.vo.ProductVO;
import com.example.tomatomall.vo.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/recommend")
public class RecommendController {
    @Resource
    RecommendService recommendService;

    /**
     * 获取推荐商品（实际上是排行榜）
     */
    @GetMapping
    public Response<List<ProductVO>> getRecommend(){
        return Response.buildSuccess(recommendService.getEverydayRecommend());
    }
}
