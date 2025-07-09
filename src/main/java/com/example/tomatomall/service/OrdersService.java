package com.example.tomatomall.service;

import com.example.tomatomall.enums.PaymentMethodEnum;
import com.example.tomatomall.vo.AccountVO;
import com.example.tomatomall.vo.OrdersVO;
import com.example.tomatomall.vo.Response;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrdersService {
    //创建订单（奇妙的命名）
    Boolean getOrder(OrdersVO ordersVO);

    //获取订单
    OrdersVO showOrder(int userId);

    //获取总金额
    double getTotalMoney(int orderId);

    //支付
    PaymentMethodEnum getPaymentMethod(int orderId);

    // 更新订单状态（注意幂等性，防止重复处理）
    void updateOrderStatus(String orderId, String alipayTradeNo, String amount);

    // 扣减库存（建议加锁或乐观锁）
    void reduceStock(String orderId);

    void updateFailture(String orderId);
}
