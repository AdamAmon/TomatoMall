package com.example.tomatomall.controller;

import com.example.tomatomall.service.AdvertisementService;
import com.example.tomatomall.vo.AdvertisementVO;
import com.example.tomatomall.vo.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/advertisements")
public class AdvertisementController {
    @Resource
    AdvertisementService advertisementService;

    /**
     * 获取所有广告
     */
    @GetMapping
    public Response<List<AdvertisementVO>> getAllAdv() {
        return Response.buildSuccess(advertisementService.getAllAdv());
    }

    /**
     * 获取单个广告
     */
    @GetMapping("/single")
    public Response<AdvertisementVO> getSingleAdv(@RequestParam("advId") int advId) {
        return Response.buildSuccess(advertisementService.getSingleAdv(advId));
    }

    /**
     * 更新广告
     */
    @PutMapping
    public Response<String> updateAdv(@RequestBody AdvertisementVO advVO) {
        Boolean flag=advertisementService.updateAdv(advVO);
        if(flag){
            return Response.buildSuccess("更新成功");
        }
        return Response.buildFailure("商品不存在","400");
    }


    /**
     * 创建广告
     */
    @PostMapping
    public Response<AdvertisementVO> createAdv(@RequestBody AdvertisementVO advVO) {
//        Boolean flag=advertisementService.createAdv(advVO);
//        if(flag){
//            return Response.buildSuccess("创建成功");
//        }else{
//            return Response.buildFailure("商品不存在","400");
//        }
        AdvertisementVO adv=advertisementService.createAdv(advVO);
        if(adv==null){
            return Response.buildFailure("商品不存在","400");
        }else{
            return Response.buildSuccess(adv);
        }
    }

    /**
     * 删除广告
     */
    @DeleteMapping
    public Response<String> deleteAdv(@RequestParam("advId") int advId) {//删除商品时自动删除
        Boolean flag=advertisementService.deleteAdv(advId);
        if(flag){
            return Response.buildSuccess("删除成功");
        }
        return Response.buildFailure("广告不存在","400");
    }




}
