package com.example.tomatomall.service.serviceImpl;

import com.example.tomatomall.exception.TomatoException;
import com.example.tomatomall.po.Cart;
import com.example.tomatomall.po.CartItem;
import com.example.tomatomall.repository.*;
import com.example.tomatomall.service.AccountService;
import com.example.tomatomall.service.CartService;
import com.example.tomatomall.util.SecurityUtil;
import com.example.tomatomall.vo.AccountVO;
import com.example.tomatomall.vo.ProductVO;
import com.example.tomatomall.vo.StockpileVO;
import com.example.tomatomall.vo.CartItemVO;
import com.example.tomatomall.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tomatomall.repository.CartRepository;
import com.example.tomatomall.exception.TomatoException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServicelmpl implements CartService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    StockpileRepository stockpileRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    SecurityUtil securityUtil;

    //将商品加入到购物车（抽象的命名）
    public CartVO getProduct(int userId, String productId, int quantity){

        int pro_id = Integer.parseInt(productId);
        CartVO cartVO = getCart(userId);
        StockpileVO stockpileVO = stockpileRepository.findByProductId(pro_id).toVO();
        int num_avil = stockpileVO.getAmount()-stockpileVO.getFrozen();
        //判定获取的数量是否大于库存
        if(num_avil < quantity){
            throw TomatoException.OutOfStock();
        }else{
            stockpileVO.setFrozen(stockpileVO.getFrozen()+quantity);
            stockpileRepository.save(stockpileVO.toPO());


            List<CartItem> l = cartItemRepository.findAllByCartId(cartVO.getCartId());
            int flag = -1;
            //查找商品，有则修改库存
            for(int i = 0; i < l.size(); i++){
                if(l.get(i).getProductId() == pro_id){
                    int q = l.get(i).getQuantity();
                    int itemId = l.get(i).getItemId();
                    CartItem cartItem_a = cartItemRepository.findByItemId(itemId);
                    cartItem_a.setQuantity(q+quantity);
                    flag = 1;
                    cartItemRepository.save(cartItem_a);
                    break;
                }
            }
            //若没有则添加
            if(flag == -1){
                CartItemVO cartItemVO = new CartItemVO();
                cartItemVO.setProductId(pro_id);
                cartItemVO.setQuantity(quantity);
                cartItemVO.setCartId(cartVO.getCartId());
                cartItemRepository.save(cartItemVO.toPO());
            }

            //pro_id需要加
            return cartVO;
        }
    }

    public Boolean deleteCartProduct(int userId, String cart_item_id){
        int cartItemId = Integer.parseInt(cart_item_id);
        CartVO cartVO = getCart(userId);
        List<CartItem> l = cartItemRepository.findAllByCartId(cartVO.getCartId());

        int flag = -1;
        for(int i=0;i<l.size();i++){
            if(l.get(i).getItemId() == cartItemId){
                StockpileVO stockpileVO = stockpileRepository.findByProductId(l.get(i).getProductId()).toVO();
                int frozen_now = stockpileVO.getFrozen();
                stockpileVO.setFrozen(frozen_now-l.get(i).getQuantity());
                stockpileRepository.save(stockpileVO.toPO());
                flag = i;
                break;
            }
        }
        if(flag == -1){
            return false;
        }
        cartItemRepository.delete(l.get(flag));
        return true;
    }

    public Boolean changeCartItemQuantity(int userId, String cart_item_id, int quantity){
        int cartItemId_n = Integer.parseInt(cart_item_id);
        CartVO cartVO = getCart(userId);
        List<CartItem> l = cartItemRepository.findAllByCartId(cartVO.getCartId());

        int flag = -1;
        for(int i=0;i<l.size();i++){
            if(l.get(i).getItemId() == cartItemId_n){
                //ProductVO productVO = productRepository.findById(l.get(i).getProductId()).toVO();
                StockpileVO stockpileVO = stockpileRepository.findByProductId(l.get(i).getProductId()).toVO();
                int amount_n = stockpileVO.getAmount();
                int frozen_n = stockpileVO.getFrozen();
                int quantity_n = l.get(i).getQuantity();
                if(amount_n < quantity-quantity_n){
                    return false;
                }//库存不够
                int frozen_new = frozen_n + (quantity-quantity_n);
                stockpileVO.setFrozen(frozen_new);
                stockpileRepository.save(stockpileVO.toPO());

                l.get(i).setQuantity(quantity);
                flag = i;
                break;
            }
        }
        if(flag == -1){
            return false;
        }
        cartItemRepository.save(l.get(flag));
        return true;
    }

    public CartVO getCart(int userId){
        Cart cart = cartRepository.findByUserId(userId);
        if(cart == null){
            CartVO cartVO = new CartVO();
            cartVO.setUserId(userId);
            cartRepository.save(cartVO.toPO());

            Cart cart1 = cartRepository.findByUserId(userId);
            if(cart1 == null){
                throw new TomatoException("fail to create cart");
            }
            return cartVO;
        } else{
            return cart.toVO();
        }
    }

    public List<CartItemVO> getAllItems(int cartId){
        List<CartItem> items = cartItemRepository.findAllByCartId(cartId);
        List<CartItemVO> itemVOs = new ArrayList<>();
        for(int i = 0; i < items.size(); i++){
            itemVOs.add(items.get(i).toVO());
        }
        return itemVOs;
    }

}
