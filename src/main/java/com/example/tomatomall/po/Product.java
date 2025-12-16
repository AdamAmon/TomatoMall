package com.example.tomatomall.po;

import com.example.tomatomall.enums.TagEnum;
import com.example.tomatomall.vo.CommentVO;
import com.example.tomatomall.vo.InfoVO;
import com.example.tomatomall.vo.ProductVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id")
    private int id;

    @Basic
    @Column(name="title")
    private String title;

    @Basic
    @Column(name="price")
    private double price;

    @Basic
    @Column(name="rate")
    private float rate;

    @Basic
    @Column(name="commentNum")
    private int commentNum;

    @Basic
    @Column(name="description")
    private String description;

    @Basic
    @Column(name="cover")
    private String cover;

    @Basic
    @Column(name="detail")
    private String detail;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    //@JoinColumn(name="specifications")
    private List<Info> specifications;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="stockpile")
    private Stockpile stockpile;

    @Basic
    @Column(name = "recommendTicket")
    private Integer recommendTicket;

    @Basic
    @Column(name = "tag")
    @Enumerated(EnumType.STRING)
    private TagEnum tag;

    @OneToMany(mappedBy = "theProduct", cascade = CascadeType.PERSIST)
    @ToString.Exclude

    private List<Comment> comments;

    public ProductVO toVO(){
        ProductVO productVO=new ProductVO();
        productVO.setId(this.id);
        productVO.setTitle(this.title);
        productVO.setPrice(this.price);
        productVO.setRate(this.rate);
        productVO.setCommentNum(this.commentNum);
        productVO.setDescription(this.description);
        productVO.setCover(this.cover);
        productVO.setDetail(this.detail);
        List<InfoVO> lists=new LinkedList<>();
        for(int i=0;i<specifications.size();i++){
            lists.add(specifications.get(i).toVO());
        }
        productVO.setSpecifications(lists);
        if(this.stockpile!=null){
            productVO.setStockpile(this.stockpile.toVO());
        }

        productVO.setRecommendTicket(this.recommendTicket);
        productVO.setTag(this.tag);
        List<CommentVO> commentVOs=new LinkedList<>();
        if(this.comments!=null){
            for(int i=0;i<this.comments.size();i++){
                commentVOs.add(this.comments.get(i).toVO());
            }
        }

        productVO.setComments(commentVOs);
        return productVO;
    }
}
