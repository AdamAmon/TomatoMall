package com.example.tomatomall.service;

import com.example.tomatomall.vo.AdvertisementVO;

import java.util.List;
public interface AdvertisementService {
    //获取所有广告
    List<AdvertisementVO> getAllAdv();

    //获取某个广告
    AdvertisementVO getSingleAdv(int advId);

    //更新广告
    Boolean updateAdv(AdvertisementVO advVO);

    //创建广告
    AdvertisementVO createAdv(AdvertisementVO advVO);

    //删除评论
    Boolean deleteAdv(int advId);
}
