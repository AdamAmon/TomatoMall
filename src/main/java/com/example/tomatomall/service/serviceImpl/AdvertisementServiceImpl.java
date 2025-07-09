package com.example.tomatomall.service.serviceImpl;

import com.example.tomatomall.exception.TomatoException;
import com.example.tomatomall.po.Advertisement;
import com.example.tomatomall.po.Product;
import com.example.tomatomall.repository.ProductRepository;
import com.example.tomatomall.repository.AdvertisementRepository;
import com.example.tomatomall.service.AdvertisementService;
import com.example.tomatomall.service.ProductService;
import com.example.tomatomall.util.SecurityUtil;
import com.example.tomatomall.util.TokenUtil;
import com.example.tomatomall.vo.AccountVO;
import com.example.tomatomall.vo.ProductVO;
import com.example.tomatomall.vo.AdvertisementVO;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.LinkedList;
import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {
    @Autowired
    AdvertisementRepository advertisementRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<AdvertisementVO> getAllAdv(){
        List<Advertisement> advs=advertisementRepository.findAll();
        List<AdvertisementVO> advVOs=new LinkedList<>();
        for(int i=0; i<advs.size(); i++){
            advVOs.add(advs.get(i).toVO());
        }
        return advVOs;
    }

    @Override
    public AdvertisementVO getSingleAdv(int advId){
        Advertisement adv=advertisementRepository.findByAdvId(advId);
        if(adv==null){
            throw TomatoException.NoSuchAdv();
        }
        return adv.toVO();
    }

    @Override
    public Boolean updateAdv(AdvertisementVO advVO){
        Advertisement adv=advertisementRepository.findByAdvId(advVO.getAdvId());
        if(adv == null){
            return false;
        }
        if(advVO.getTitle()!=null){
            adv.setTitle(advVO.getTitle());
        }
        if(advVO.getContent()!=null){
            adv.setContent(advVO.getContent());
        }
        if(advVO.getAdvUrl()!=null){
            adv.setAdvUrl(advVO.getAdvUrl());
        }
        if(advVO.getProductId()!=0){
            adv.setProductId(advVO.getProductId());
        }
        advertisementRepository.save(adv);
        return true;
    }

    @Override
    public AdvertisementVO createAdv(AdvertisementVO advVO){
        Product product=productRepository.findById(advVO.getProductId());
        if(product == null){
            return null;
        }
        List<Advertisement> advs = advertisementRepository.findAllByProductId(advVO.getProductId());
        //若商品已存在，则覆盖为新广告，否则创建新广告
        if(!advs.isEmpty()){//更新
            if(advs.size() >= 2){
                throw TomatoException.TooManyAdvs();
            }
            Advertisement adv_tt=advs.get(0);
            adv_tt.setTitle(advVO.getTitle());
            adv_tt.setContent(advVO.getContent());
            adv_tt.setAdvUrl(advVO.getAdvUrl());
            adv_tt.setProductId(advVO.getProductId());
            Advertisement aa = advertisementRepository.save(adv_tt);
            return aa.toVO();

        }else{//添加
            Advertisement adv=advVO.toPO();
            Advertisement aa = advertisementRepository.save(adv);
            return aa.toVO();
        }
    }

    @Override
    public Boolean deleteAdv(int advId){
        Advertisement adv=advertisementRepository.findByAdvId(advId);
        if(adv==null){
            return false;
        }else{
            advertisementRepository.delete(adv);
            return true;
        }
    }
}
