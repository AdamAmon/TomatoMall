package com.example.tomatomall.po;

import com.example.tomatomall.vo.CommentVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id")
    private int id;

    @Basic
    @Column(name = "productId")
    private Integer productId;

    @Basic
    @Column(name = "userId")
    private Integer userId;

    @Basic
    @Column(name="rate")
    private float rate;

    @Basic
    @Column(name = "time")
    private long time;

    @Basic
    @Column(name="content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "theProduct")
    private Product theProduct;

    @ManyToOne
    @JoinColumn(name = "user")
    private Account user;

    public CommentVO toVO(){
        CommentVO commentVO=new CommentVO();
        commentVO.setId(this.id);
        commentVO.setProductId(this.productId);
        commentVO.setUserId(this.userId);
        commentVO.setRate(this.rate);
        commentVO.setTime(this.time);
        commentVO.setContent(this.content);
        commentVO.setTheProduct(this.theProduct);
        commentVO.setUser(this.user);
        return commentVO;
    }
}
