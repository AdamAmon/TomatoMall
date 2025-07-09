package com.example.tomatomall.service;

import com.example.tomatomall.vo.AccountVO;
import com.example.tomatomall.vo.CommentVO;

import java.util.List;

public interface AccountService {
    //注册
    Boolean register(AccountVO accountVO);

    //登录
    String login(String username,String password);

    //获取信息
    AccountVO getInformation();

    //更新信息
    Boolean update(AccountVO accountVO);

    //获取推荐票
    Boolean getRecommendTicket(String userId,int num);

    //使用推荐票
    Boolean useRecommendTicket(String userId,String productId);

    //评论
    Boolean makeComment(String userId,String productId , String content,float rate);

    //删除评论
    Boolean deleteComment(String id);

    //获取用户的所有评论
    List<CommentVO> getAllComments(String userId);

    //更新评论
    CommentVO updateComment(String id,String content ,float rate);

    //vip升级
    Boolean vipUpdate(String amount, int userId);
}
