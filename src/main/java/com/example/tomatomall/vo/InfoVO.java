package com.example.tomatomall.vo;


import com.example.tomatomall.po.Info;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InfoVO {
    private int id;

    private String item;

    private String value;

    private int productId;

    public Info toPO(){
        Info info=new Info();
        info.setId(this.id);
        info.setItem(this.item);
        info.setValue(this.value);
        info.setProductId(this.productId);
        return info;
    }
}
