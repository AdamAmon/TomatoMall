package com.example.tomatomall.po;

import com.example.tomatomall.vo.InfoVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Info {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id")
    private int id;

    @Basic
    @Column(name="item")
    private String item;

    @Basic
    @Column(name="value")
    private String value;

    @Basic
    @Column(name="productId")
    private int productId;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    public InfoVO toVO(){
        InfoVO infoVO=new InfoVO();
        infoVO.setId(this.id);
        infoVO.setItem(this.item);
        infoVO.setValue(this.value);
        infoVO.setProductId(this.productId);
        return infoVO;
    }
}
