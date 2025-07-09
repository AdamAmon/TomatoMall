package com.example.tomatomall.service.serviceImpl;

import com.alipay.api.domain.OrderVO;
import com.example.tomatomall.enums.PayEnum;
import com.example.tomatomall.enums.PaymentMethodEnum;
import com.example.tomatomall.po.CartItem;
import com.example.tomatomall.service.CartService;
import com.example.tomatomall.service.OrdersService;
import com.example.tomatomall.vo.AccountVO;
import com.example.tomatomall.vo.OrdersVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.example.tomatomall.exception.TomatoException;
import com.example.tomatomall.po.Orders;
import com.example.tomatomall.repository.*;
import com.example.tomatomall.util.SecurityUtil;
import com.example.tomatomall.vo.AccountVO;
import com.example.tomatomall.vo.ProductVO;
import com.example.tomatomall.vo.StockpileVO;
import com.example.tomatomall.vo.OrdersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tomatomall.exception.TomatoException;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    StockpileRepository stockpileRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SecurityUtil securityUtil;

    @Override
    public Boolean getOrder(OrdersVO ordersVO) {
        List<Orders> o = ordersRepository.findByUserId(ordersVO.getUserId());
        //若存在，则覆写支付单
        if(!o.isEmpty()){
            if(o.get(o.size()-1).getStatus() == PayEnum.WAIT_BUYER_PAY){
                o.get(o.size()-1).setStatus(PayEnum.TRADE_FINISHED);
            }
        }


        ordersVO.setCreate_time(new Date());
        ordersVO.setStatus(PayEnum.WAIT_BUYER_PAY);
        ordersRepository.save(ordersVO.toPO());
        return true;
    }

    public OrdersVO showOrder(int userId){
        //获取支付单
        List<Orders> orders_list =  ordersRepository.findAllByUserId(userId);
        //List<OrdersVO> ordersVO_list = new ArrayList<>();

        if(orders_list.isEmpty()){
            return null;
        }
        return orders_list.get(orders_list.size()-1).toVO();
    }

    @Override
    public double getTotalMoney(int orderId){
        Orders o = ordersRepository.findById(orderId);
        return o.getTotal_amount();
    }

    @Override
    public PaymentMethodEnum getPaymentMethod(int orderId){
        Orders o = ordersRepository.findById(orderId);
        return o.getPayment_method();
    }

    @Override
    // 更新订单状态（注意幂等性，防止重复处理）
    public void updateOrderStatus(String orderId, String alipayTradeNo, String amount){
        int order_id = Integer.parseInt(orderId);
        Orders o = ordersRepository.findById(order_id);
        if(o.getStatus() != PayEnum.TRADE_SUCCESS){
            o.setStatus(PayEnum.TRADE_SUCCESS);
            ordersRepository.save(o);
        }
    }

    @Override
    // 扣减库存（建议加锁或乐观锁）
    public void reduceStock(String orderId){
        int order_id = Integer.parseInt(orderId);
        Orders o = ordersRepository.findById(order_id);
        List<Integer> itemsId = o.getItemsId();
        for(int i : itemsId){
            CartItem cartItem = cartItemRepository.findByItemId(i);
            int productId = cartItem.getProductId();
            int quantity = cartItem.getQuantity();
            StockpileVO stockpileVO = stockpileRepository.findByProductId(productId).toVO();
            int amount_now = stockpileVO.getAmount();
            stockpileVO.setAmount(amount_now-quantity);
            stockpileRepository.save(stockpileVO.toPO());
        }
        o.setItemsId(null);
        ordersRepository.save(o);
    }

    @Override
    public void updateFailture(String orderId){
        int order_id = Integer.parseInt(orderId);
        Orders o = ordersRepository.findById(order_id);
        if(o.getStatus() != PayEnum.TRADE_CLOSED){
            o.setStatus(PayEnum.TRADE_CLOSED);
            ordersRepository.save(o);
        }
    }

}
