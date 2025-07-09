package com.example.tomatomall.service.serviceImpl;

import com.example.tomatomall.po.Info;
import com.example.tomatomall.po.Product;
import com.example.tomatomall.repository.InfoRepository;
import com.example.tomatomall.repository.ProductRepository;
import com.example.tomatomall.service.RecommendService;
import com.example.tomatomall.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    InfoRepository infoRepository;

    @Override
    public List<ProductVO> getEverydayRecommend(){
        List<Product> products=productRepository.findAll();

        //排序
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return Integer.compare(o2.getRecommendTicket(),o1.getRecommendTicket());
            }
        });

        //选前5
        List<ProductVO> productVOs=new LinkedList<>();
        if(products.size()>5){
            for(int i=0;i<5;i++){
                List<Info> infos=infoRepository.findAllByProductId(products.get(i).getId());
                products.get(i).setSpecifications(infos);
                productVOs.add(products.get(i).toVO());
            }
        }
        else {
            for(int i=0;i<products.size();i++){
                List<Info> infos=infoRepository.findAllByProductId(products.get(i).getId());
                products.get(i).setSpecifications(infos);
                productVOs.add(products.get(i).toVO());
            }
        }

        return productVOs;
    };
}
