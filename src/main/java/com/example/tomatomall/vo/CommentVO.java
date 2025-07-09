package com.example.tomatomall.vo;

import com.example.tomatomall.po.Account;
import com.example.tomatomall.po.Comment;
import com.example.tomatomall.po.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentVO {

    private int id;

    private Integer productId;

    private Integer userId;

    private float rate;

    private long time;

    private String content;

    private Product theProduct;

    private Account user;

    public Comment toPO(){
        Comment comment=new Comment();
        comment.setId(this.id);
        comment.setProductId(this.productId);
        comment.setUserId(this.userId);
        comment.setRate(this.rate);
        comment.setTime(this.time);
        comment.setContent(this.content);
        comment.setTheProduct(this.theProduct);
        comment.setUser(this.user);
        return comment;
    }
}
