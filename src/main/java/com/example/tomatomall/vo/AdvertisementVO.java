package com.example.tomatomall.vo;
import com.example.tomatomall.po.Advertisement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class AdvertisementVO {
    private int advId;

    private String title;

    private String content;

    private String advUrl;

    private int productId;

    public Advertisement toPO() {
        Advertisement adv = new Advertisement();
        adv.setAdvId(this.advId);
        adv.setTitle(this.title);
        adv.setContent(this.content);
        adv.setAdvUrl(this.advUrl);
        adv.setProductId(this.productId);
        return adv;
    }
}
