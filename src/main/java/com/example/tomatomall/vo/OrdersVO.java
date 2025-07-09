package com.example.tomatomall.vo;

import com.example.tomatomall.enums.PayEnum;
import com.example.tomatomall.enums.PaymentMethodEnum;
import com.example.tomatomall.po.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrdersVO {
    private int id;

    private int userId;

    private List<Integer> itemsId;

    private double total_amount;

    private PaymentMethodEnum payment_method;

    private PayEnum status;

    private Date create_time;

    public Orders toPO(){
        Orders orders = new Orders();
        orders.setId(this.id);
        orders.setUserId(this.userId);
        orders.setItemsId(this.itemsId);
        orders.setTotal_amount(this.total_amount);
        orders.setPayment_method(this.payment_method);
        orders.setStatus(this.status);
        orders.setCreate_time(this.create_time);
        return orders;
    }
}
