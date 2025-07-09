package com.example.tomatomall.po;

import com.example.tomatomall.vo.OrdersVO;
import com.example.tomatomall.enums.PaymentMethodEnum;
import com.example.tomatomall.enums.PayEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Orders {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id")
    private int id;

    @Basic
    @Column(name="userId")
    private int userId;

    @ElementCollection
    @Column(name = "item_id") // 存储 List 中元素的列名
    private List<Integer> itemsId;

    @Basic
    @Column(name="total_amount")
    private double total_amount;

    @Basic
    @Column(name="payment_method")
    private PaymentMethodEnum payment_method;

    @Basic
    @Column(name="status")
    private PayEnum status;

    @Basic
    @Column(name="create_time")
    private Date create_time;
    public OrdersVO toVO(){
        OrdersVO ordersVO = new OrdersVO();
        ordersVO.setId(this.id);
        ordersVO.setUserId(this.userId);
        ordersVO.setItemsId(this.itemsId);
        ordersVO.setTotal_amount(this.total_amount);
        ordersVO.setPayment_method(this.payment_method);
        ordersVO.setStatus(this.status);
        ordersVO.setCreate_time(this.create_time);
        return ordersVO;
    }
}
