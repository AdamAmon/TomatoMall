package com.example.tomatomall.controller;

import com.example.tomatomall.service.AccountService;
import com.example.tomatomall.vo.AccountVO;
import com.example.tomatomall.vo.CommentVO;
import com.example.tomatomall.vo.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Resource
    AccountService accountService;

    /**
     * 获取用户详情
     */
    @GetMapping("/{username}")
    public Response<AccountVO> getAccount() {
        return Response.buildSuccess(accountService.getInformation());
    }

    /**
     * 创建新的用户
     */
    @PostMapping
    public Response<String> createUser(@RequestBody AccountVO accountVO) {
        if(accountService.register(accountVO)){
            return Response.buildSuccess("注册成功");
        }
        else {
            return null;
        }
    }

    /**
     * 更新用户信息
     */
    @PutMapping
    public Response<Boolean> updateUser(@RequestBody AccountVO accountVO) {

        return Response.buildSuccess(accountService.update(accountVO));
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Response<String> login(@RequestBody AccountVO accountVO ) {

        return Response.buildSuccess(accountService.login(accountVO.getUsername(),accountVO.getPassword()));
    }

    /**
     * 获取推荐票
     */
    @PostMapping("/ticket/get")
    public Response<Boolean> getRecommendTicket(@RequestParam("userId")String userId,@RequestParam("num")int num){
        return Response.buildSuccess(accountService.getRecommendTicket(userId,num));
    }

    /**
     * 使用推荐票
     */
    @PostMapping("/ticket/use")
    public Response<Boolean> useRecommendTicket(@RequestParam("userId")String userId,@RequestParam("productId")String productId){
        return Response.buildSuccess(accountService.useRecommendTicket(userId,productId));
    }

    /**
     * 评论
     */
    @PostMapping("/comment/make")
    public Response<Boolean> makeComment(@RequestParam("userId") String userId,@RequestParam("productId")String productId,@RequestParam("content")String content,@RequestParam("rate")float rate){
        return Response.buildSuccess(accountService.makeComment(userId,productId,content,rate));
    }

    /**
     * 删除评论
     */
    @PostMapping("/comment/delete")
    public Response<Boolean> deleteComment(@RequestParam("id")String id){
        return Response.buildSuccess(accountService.deleteComment(id));
    }

    /**
     * 获取用户的所有评论
     */
    @GetMapping("/comment")
    public Response<List<CommentVO>> getAllComment(@RequestParam("userId")String userId){
        return Response.buildSuccess(accountService.getAllComments(userId));
    }

    /**
     * 更新评论
     */
    @GetMapping("/comment/update")
    public Response<CommentVO> updateComment(@RequestParam("id")String id,@RequestParam("content")String content,@RequestParam("rate")float rate){
        return Response.buildSuccess(accountService.updateComment(id,content,rate));
    }

    /**
     * vip等级提升
     */
    @PostMapping("/vip")
    public Response<Boolean> updateVIP(@RequestParam("amount")String amount,@RequestParam("userId")Integer userId){
        return Response.buildSuccess(accountService.vipUpdate(amount, userId));
    }
}
