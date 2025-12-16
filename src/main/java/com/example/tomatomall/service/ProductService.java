package com.example.tomatomall.service;

import com.example.tomatomall.enums.TagEnum;
import com.example.tomatomall.vo.CommentVO;
import com.example.tomatomall.vo.ProductVO;
import com.example.tomatomall.vo.StockpileVO;

import java.util.List;

public interface ProductService {
    //获取所有商品
    List<ProductVO> getAllProduct();

    //获取某个商品的信息
    ProductVO getOneProduct(String id);

    //更新商品信息
    Boolean updateProduct(ProductVO productVO);

    //创建商品
    ProductVO createProduct(ProductVO productVO);

    //删除商品
    Boolean deleteProduct(String id);

    //更新库存
    Boolean updateAmount(String id , int amount);

    //获取某个商品的库存
    StockpileVO getOneProductAmount(String id);

    //模糊搜索
    List<ProductVO> getProductsContainingName(String name);

    //按标签搜索
    List<ProductVO> getProductByTag(TagEnum tag);

    //获取商品的所有评论
    List<CommentVO> getAllComments(String productId);
}
