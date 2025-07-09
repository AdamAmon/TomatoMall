package com.example.tomatomall.controller;

import com.example.tomatomall.po.CartItem;
import com.example.tomatomall.service.CartService;
import com.example.tomatomall.vo.Response;
import com.example.tomatomall.vo.CartVO;
import com.example.tomatomall.vo.CartItemVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Resource
    CartService cartService;

    /**
     * 将商品加入购物车
     */
    @PostMapping
    public Response<CartVO> addProductToCart(@RequestParam("userId") int userId, @RequestParam("productId") String productId, @RequestParam("quantity") Integer quantity) {
        return Response.buildSuccess(cartService.getProduct(userId, productId, quantity));
    }

    /**
     * 删除购物车中的某个商品
     */
    @DeleteMapping
    public Response<String> deleteCartItem(@RequestParam("userId") int userId, @RequestParam String cart_item_id){
        Boolean flag = cartService.deleteCartProduct(userId, cart_item_id);
        if(flag){
            return Response.buildSuccess("删除成功");
        }else{
            return Response.buildFailure("购物车商品不存在", "400");
        }
    }

    /**
     * 改变购物车商品的数量
     */
    @PatchMapping("/quantity")
    public Response<String> changeCartItemQuantity(@RequestParam("userId") int userId, @RequestParam("cart_item_id") String cart_item_id, @RequestParam("quantity") Integer quantity){
        Boolean flag = cartService.changeCartItemQuantity(userId, cart_item_id, quantity);
        if(flag){
            return Response.buildSuccess("修改数量成功");
        }else{
            return Response.buildFailure("购物车商品不存在", "400");
        }
    }

    /**
     * 获取购物车id与相应的用户id
     */
    @GetMapping
    public Response<CartVO> getCart(@RequestParam("userId") int userId){
        return Response.buildSuccess(cartService.getCart(userId));
    }

    /**
     * 获取购物车内的商品
     */
    @GetMapping("/getAll")
    public Response<List<CartItemVO>> getAllItems(@RequestParam("cartId") int cartId){
        return Response.buildSuccess(cartService.getAllItems(cartId));
    }
}