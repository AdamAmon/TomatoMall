package com.example.tomatomall.service.serviceImpl;

import com.example.tomatomall.exception.TomatoException;
import com.example.tomatomall.po.*;
import com.example.tomatomall.repository.*;
import com.example.tomatomall.service.AccountService;
import com.example.tomatomall.util.SecurityUtil;
import com.example.tomatomall.util.TokenUtil;
import com.example.tomatomall.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.tomatomall.enums.PayEnum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    SecurityUtil securityUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Boolean register(AccountVO accountVO){

        //初始化
        Account account=accountRepository.findByUsername(accountVO.getUsername());
        if(account != null){
            throw TomatoException.usernameAlreadyExits();
        }
        Account newaccount =accountVO.toPO();
        String rawpassword = newaccount.getPassword();
        String newpassword = passwordEncoder.encode(rawpassword);
        newaccount.setPassword(newpassword);

        //VIP内容
        newaccount.setVip(0);
        newaccount.setExpe(0);
        newaccount.setRecommendTicket(0);
        newaccount.setMoneyProgress(0);

        Account A = accountRepository.save(newaccount);

        //购物车
        CartVO cartVO = new CartVO();
        cartVO.setUserId(A.getId());
        cartRepository.save(cartVO.toPO());
//        CartItem cartItem = new CartItem();
//        cartItem.setQuantity(1);
//        cartItem.setCartId(C.getCartId());
//        cartItemRepository.save(cartItem);

        //CartVO cartVO = new CartVO();
        //cartVO.setUserId(A.getId());
        //throw new TomatoException("保存失败");
        //cartRepository.save(cartVO.toPO());

        return true;
    }

    @Override
    public String login(String username,String password){

        //Account account=accountRepository.findByUsernameAndPassword(username,password);
        //if(account==null){
        //    throw TomatoException.PhoneOrPasswordError();
        //}

        Account account=accountRepository.findByUsername(username);
        //验证密码
        if(account!=null){
            if (passwordEncoder.matches(password,account.getPassword())){
                return tokenUtil.getToken(account);
            }
        }
        throw TomatoException.UsernameOrPasswordError();
    }

    @Override
    public AccountVO getInformation(){
        Account account=securityUtil.getCurrentAccount();
        return account.toVO();
    }

    @Override
    public Boolean update(AccountVO accountVO){
        Account account=securityUtil.getCurrentAccount();
        if (accountVO.getPassword()!=null){
            String rawpassword = accountVO.getPassword();
            String newpassword = passwordEncoder.encode(rawpassword);
            accountVO.setPassword(newpassword);
            account.setPassword(accountVO.getPassword());
        }
        if (accountVO.getName()!=null){
            account.setName(accountVO.getName());
        }
        if (accountVO.getAddress()!=null){
            account.setAddress(accountVO.getAddress());
        }
        if (accountVO.getAvatar()!=null){
            account.setAvatar(accountVO.getAvatar());
        }
        if (accountVO.getEmail()!=null){
            account.setEmail(accountVO.getEmail());
        }
        if(accountVO.getPhone()!=null){
            account.setPhone(accountVO.getPhone());
        }
        accountRepository.save(account);
        return true;
    }

    @Override
    public Boolean getRecommendTicket(String userId,int num){
        int toUserId=Integer.parseInt(userId);
        Account account=accountRepository.findById(toUserId);
        account.setRecommendTicket(account.getRecommendTicket()+num);
        accountRepository.save(account);
        return true;
    };

    @Override
    public Boolean useRecommendTicket(String userId,String productId){
        int toUserId=Integer.parseInt(userId);
        int toProductId=Integer.parseInt(productId);
        Account account=accountRepository.findById(toUserId);
        Product product=productRepository.findById(toProductId);
        if(account==null||product==null){
            throw TomatoException.NoUserOrProduct();
        }
        if(account.getRecommendTicket()==0){
            throw TomatoException.NoRecommendTicket();
        }
        account.setRecommendTicket(account.getRecommendTicket()-1);
        product.setRecommendTicket(product.getRecommendTicket()+1);
        accountRepository.save(account);
        productRepository.save(product);
        return true;
    };

    @Override
    public Boolean makeComment(String userId,String productId , String content,float rate){
        //评论初始化
        Comment comment=new Comment();
        comment.setContent(content);
        comment.setUserId(Integer.parseInt(userId));
        comment.setProductId(Integer.parseInt(productId));
        comment.setRate(rate);
        comment.setTime(System.currentTimeMillis());
        commentRepository.save(comment);

        Product product=productRepository.findById(Integer.parseInt(productId));
        float rat=product.getRate()*product.getCommentNum()+rate;
        product.setCommentNum(product.getCommentNum()+1);
        product.setRate(rat/product.getCommentNum());
        productRepository.save(product);
        return true;
    };

    @Override
    public Boolean deleteComment(String id){
        Comment comment=commentRepository.findById(Integer.parseInt(id));
        Product product=productRepository.findById(comment.getProductId().intValue());
        float rat=product.getRate()*product.getCommentNum()-comment.getRate();
        product.setCommentNum(product.getCommentNum()-1);
        //更新商品的评分
        if (product.getCommentNum()==0){
            product.setRate(0);
        }else {
            product.setRate(rat/product.getCommentNum());
        }

        productRepository.save(product);
        commentRepository.delete(comment);
        return true;
    };

    @Override
    public List<CommentVO> getAllComments(String userId){
        int toId=Integer.parseInt(userId);
        List<Comment> comments=commentRepository.findByUserId(toId);
        if (comments==null){
            return null;
        }
        List<CommentVO> commentVOS=new LinkedList<>();
        for(int i=0;i<comments.size();i++){
            commentVOS.add(comments.get(i).toVO());
        }
        return commentVOS;
    };

    @Override
    public CommentVO updateComment(String id,String content,float rate){
      int toId=Integer.parseInt(id);
      Comment comment=commentRepository.findById(toId);
      Product product=productRepository.findById(comment.getProductId().intValue());
      float rat=product.getRate()*product.getCommentNum()-comment.getRate()+rate;
      product.setRate(rat/product.getCommentNum());
      productRepository.save(product);
      comment.setRate(rate);
      comment.setContent(content);
      Comment comment1=commentRepository.save(comment);
      return comment1.toVO();
    };

    @Override
    public Boolean vipUpdate(String amount, int userId){
        int total_amount = Integer.parseInt(amount);
        AccountVO accountVO = accountRepository.findById(userId).toVO();
        int vip = accountVO.getVip();
        int expe = accountVO.getExpe();
        expe += total_amount;
        if(expe >= 100){
            vip += expe/100;
            expe = expe%100;
        }
        Account account = accountVO.toPO();
        account.setVip(vip);
        account.setExpe(expe);
        accountRepository.save(account);
        return true;
    }
}
