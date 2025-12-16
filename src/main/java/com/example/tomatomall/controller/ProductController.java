package com.example.tomatomall.controller;

import com.example.tomatomall.enums.TagEnum;
import com.example.tomatomall.service.ProductService;
import com.example.tomatomall.vo.CommentVO;
import com.example.tomatomall.vo.ProductVO;
import com.example.tomatomall.vo.Response;
import com.example.tomatomall.vo.StockpileVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Resource
    ProductService productService;
    /**
     * 获取所有商品
     */
    @GetMapping
    public Response<List<ProductVO>> getAllProducts(){
        return Response.buildSuccess(productService.getAllProduct());
    }

    /**
     * 获取某个商品的信息
     */
    @GetMapping("/{id}")
    public Response<ProductVO> getOneProduct(@PathVariable("id") String id){
        return Response.buildSuccess(productService.getOneProduct(id));
    }

    /**
     * 更新商品信息
     */
    @PutMapping
    public Response<String> updateProduct(@RequestBody ProductVO productVO){
        Boolean flag=productService.updateProduct(productVO);
        if(flag){
            return Response.buildSuccess("更新成功");
        }
        return Response.buildFailure("商品不存在","400");
    }

    /**
     * 创建商品
     */
    @PostMapping
    public Response<ProductVO> createProduct(@RequestBody ProductVO productVO){
        return Response.buildSuccess(productService.createProduct(productVO));
    }

    /**
     * 删除商品
     */
    @DeleteMapping("/{id}")
    public Response<String> deleteProduct(@PathVariable("id") String id){
        Boolean flag=productService.deleteProduct(id);
        if(flag){
            return Response.buildSuccess("删除成功");
        }
        return Response.buildFailure("商品不存在","400");
    }

    /**
     * 调整商品的库存
     */
    @PatchMapping("/stockpile/{productId}")
    public Response<String> updateAmount(@PathVariable("productId") String id,@RequestBody StockpileVO stockpileVO){
        Boolean flag=productService.updateAmount(id,stockpileVO.getAmount());
        if(flag){
            return Response.buildSuccess("调整库存成功");
        }
        return Response.buildFailure("商品不存在","400");
    }

    /**
     * 获取商品库存
     */
    @GetMapping("/stockpile/{productId}")
    public Response<StockpileVO> getOneProductAmount(@PathVariable("productId") String id){
        return Response.buildSuccess(productService.getOneProductAmount(id));
    }

    /**
     * 根据名字的模糊搜索
     */
    @GetMapping("/search")
    public Response<List<ProductVO>> searchName(@RequestParam("name") String name){
        return Response.buildSuccess(productService.getProductsContainingName(name));
    }

    /**
     * 根据标签进行搜索
     */
    @GetMapping("/tag")
    public Response<List<ProductVO>> searchByTag(@RequestParam("tag")TagEnum tag){
        return Response.buildSuccess(productService.getProductByTag(tag));
    }

    /**
     * 获取关于该商品的所有评论
     */
    @GetMapping("/comment")
    public Response<List<CommentVO>> getAllComment(@RequestParam("productId")String productId){
        return Response.buildSuccess(productService.getAllComments(productId));
    }
}
