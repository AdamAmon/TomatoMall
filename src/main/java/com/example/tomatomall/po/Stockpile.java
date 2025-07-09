package com.example.tomatomall.po;

import com.example.tomatomall.vo.InfoVO;
import com.example.tomatomall.vo.StockpileVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Stockpile {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id")
    private int id;

    @Basic
    @Column(name="amount")
    private int amount;

    @Basic
    @Column(name="frozen")
    private int frozen;

    @Basic
    @Column(name="productId")
    private int productId;

    @OneToOne(mappedBy = "stockpile")
    @JoinColumn(name="product")
    private Product product;

    public StockpileVO toVO(){
        StockpileVO stockpileVO=new StockpileVO();
        stockpileVO.setId(this.id);
        stockpileVO.setAmount(this.amount);
        stockpileVO.setFrozen(this.frozen);
        stockpileVO.setProductId(this.productId);
        return stockpileVO;
    }
}
