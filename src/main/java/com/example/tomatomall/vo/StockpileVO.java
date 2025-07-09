package com.example.tomatomall.vo;

import com.example.tomatomall.po.Stockpile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StockpileVO {
    private int id;

    private int amount;

    private int frozen;

    private int productId;

    public Stockpile toPO(){
        Stockpile stockpile=new Stockpile();
        stockpile.setId(this.id);
        stockpile.setAmount(this.amount);
        stockpile.setFrozen(this.frozen);
        stockpile.setProductId(this.productId);
        return stockpile;
    }
}
