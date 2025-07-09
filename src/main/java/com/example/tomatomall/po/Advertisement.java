package com.example.tomatomall.po;

import com.example.tomatomall.vo.AdvertisementVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Advertisement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="advId")
    private int advId;

    @Basic
    @Column(name="title")
    private String title;

    @Basic
    @Column(name="content")
    private String content;

    @Basic
    @Column(name="advUrl")
    private String advUrl;

    @Basic
    @Column(name="productId")
    private int productId;

    public AdvertisementVO toVO(){
        AdvertisementVO advVO=new AdvertisementVO();
        advVO.setAdvId(this.advId);
        advVO.setTitle(this.title);
        advVO.setContent(this.content);
        advVO.setAdvUrl(this.advUrl);
        advVO.setProductId(this.productId);
        return advVO;
    }
}
