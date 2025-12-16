package com.example.tomatomall.vo;

import com.example.tomatomall.enums.TagEnum;
import com.example.tomatomall.po.Comment;
import com.example.tomatomall.po.Info;
import com.example.tomatomall.po.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductVO {
    private int id;

    private String title;

    private double price;

    private float rate;

    private int commentNum;

    private String description;

    private String cover;

    private String detail;

    private List<InfoVO> specifications;

    private StockpileVO stockpile;

    private Integer recommendTicket;

    private TagEnum tag;

    private List<CommentVO> comments;

    public Product toPO(){
        Product product=new Product();
        product.setId(this.id);
        product.setTitle(this.title);
        product.setPrice(this.price);
        product.setRate(this.rate);
        product.setCommentNum(this.commentNum);
        product.setDescription(this.description);
        product.setCover(this.cover);
        product.setDetail(this.detail);
        List<Info> lists=new LinkedList<>();
        if (this.getSpecifications()!=null){
            for(int i=0;i<this.specifications.size();i++){
                lists.add(this.specifications.get(i).toPO());
            }
            product.setSpecifications(lists);
        }

        if(this.stockpile!=null){
            product.setStockpile(this.stockpile.toPO());
        }

        product.setRecommendTicket(this.recommendTicket);
        product.setTag(this.tag);
        List<Comment> comments=new LinkedList<>();
        if(this.comments!=null){
            for(int i=0;i<this.comments.size();i++){
                comments.add(this.comments.get(i).toPO());
            }
        }

        product.setComments(comments);
        return product;
    }
}
