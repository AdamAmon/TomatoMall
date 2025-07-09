package com.example.tomatomall.service.serviceImpl;

import com.example.tomatomall.enums.TagEnum;
import com.example.tomatomall.exception.TomatoException;
import com.example.tomatomall.po.Comment;
import com.example.tomatomall.po.Info;
import com.example.tomatomall.po.Product;
import com.example.tomatomall.po.Stockpile;
import com.example.tomatomall.repository.CommentRepository;
import com.example.tomatomall.repository.InfoRepository;
import com.example.tomatomall.repository.ProductRepository;
import com.example.tomatomall.repository.StockpileRepository;
import com.example.tomatomall.service.ProductService;
import com.example.tomatomall.util.SecurityUtil;
import com.example.tomatomall.vo.CommentVO;
import com.example.tomatomall.vo.InfoVO;
import com.example.tomatomall.vo.ProductVO;
import com.example.tomatomall.vo.StockpileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class ProductServicelmpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    StockpileRepository stockpileRepository;

    @Autowired
    InfoRepository infoRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    SecurityUtil securityUtil;


    @Override
    public List<ProductVO> getAllProduct(){
        List<Product> products=productRepository.findAll();
        List<ProductVO> productVOs=new LinkedList<>();
        for(int i=0;i<products.size();i++){
            List<Info> infos=infoRepository.findAllByProductId(products.get(i).getId());
            products.get(i).setSpecifications(infos);
            productVOs.add(products.get(i).toVO());
        }
        return productVOs;
    }

    @Override
    public ProductVO getOneProduct(String id){
        int toId=Integer.parseInt(id);
        Product product=productRepository.findById(toId);
//        if(toId == 0){
//            return null;
//        }
        if(product==null){
            throw TomatoException.noProduct();
        }
        List<Info> infos=infoRepository.findAllByProductId(product.getId());
        product.setSpecifications(infos);
        return product.toVO();
    }

    @Override
    public Boolean updateProduct(ProductVO productVO){
        Product product=productRepository.findById(productVO.getId());
        if(product==null){
            return false;
        }
        if(productVO.getTitle()!=null){
            product.setTitle(productVO.getTitle());
        }
        if (productVO.getPrice()!=product.getPrice()){
            product.setPrice(productVO.getPrice());
        }
        if (productVO.getRate()!=product.getRate()){
            product.setRate(productVO.getRate());
        }
        if (productVO.getDescription()!=null){
            product.setDescription(productVO.getDescription());
        }
        if (productVO.getCover()!=null){
            product.setCover(productVO.getCover());
        }
        if(productVO.getTag()!=null){
            product.setTag(productVO.getTag());
        }
        Stockpile stockpile=stockpileRepository.findByProductId(product.getId());
        Product product1=productRepository.save(product);
        if(productVO.getSpecifications()!=null){
            int toId=productVO.getId();
            List<Info> infos=infoRepository.findAllByProductId(toId);
            if(infos!=null){
                for(int i=0;i<infos.size();i++){
                    infoRepository.delete(infos.get(i));
                }
            }
            Info info = new Info();
            for(int i=0;i<productVO.getSpecifications().size();i++){
                info=productVO.getSpecifications().get(i).toPO();
                info.setProductId(product1.getId());
                infoRepository.save(info);
            }
        }
        stockpile.setProductId(product1.getId());
        stockpileRepository.save(stockpile);
        return true;
    }

    @Override
    public ProductVO createProduct(ProductVO productVO){
        //初始化
        Product product=productVO.toPO();
        Stockpile stockpile=new Stockpile();
        stockpile.setAmount(1);
        stockpile.setFrozen(0);
        List<Info> infos=new LinkedList<>();
        if(product.getSpecifications()!=null){
            for(int i=0;i<product.getSpecifications().size();i++){
                infos.add(product.getSpecifications().get(i));
            }
        }

        product.setSpecifications(null);
        product.setStockpile(null);

        product.setRecommendTicket(0);
        product.setCommentNum(0);
        Product product1=productRepository.save(product);
        stockpile.setProductId(product1.getId());
        stockpile.setProduct(product1);
        stockpileRepository.save(stockpile);

        for(int i=0;i<infos.size();i++){
            infos.get(i).setProductId(product1.getId());
            infoRepository.save(infos.get(i));
        }
        product1.setStockpile(stockpile);
        product1.setSpecifications(infos);
        productRepository.save(product1);

        return product1.toVO();
    }

    @Override
    public Boolean deleteProduct(String id){
        int toId=Integer.parseInt(id);
        Product product=productRepository.findById(toId);
        if(product==null){
            return false;
        }
        productRepository.delete(product);
        return true;
    }

    @Override
    public Boolean updateAmount(String id , int amount){
        int toId=Integer.parseInt(id);
        Stockpile stockpile=stockpileRepository.findByProductId(toId);
        if (stockpile==null){
            return false;
        }
        stockpile.setAmount(amount);
        stockpileRepository.save(stockpile);
        return true;
    }

    @Override
    public StockpileVO getOneProductAmount(String id){
        int toId = Integer.parseInt(id);
        Stockpile stockpile = stockpileRepository.findByProductId(toId);
        return stockpile.toVO();
    }

    @Override
    public List<ProductVO> getProductsContainingName(String name){
        List<Product> products=productRepository.findByTitleContaining(name);
        List<ProductVO> productVOS=new LinkedList<>();
        for(int i=0;i<products.size();i++){
            productVOS.add(products.get(i).toVO());
        }
        return productVOS;
    }

    @Override
    public List<ProductVO> getProductByTag(TagEnum tag){
        List<Product> products=productRepository.findAllByTag(tag);
        List<ProductVO> productVOs=new LinkedList<>();
        for(int i=0;i<products.size();i++){
            productVOs.add(products.get(i).toVO());
        }
        return productVOs;
    };

    @Override
    public List<CommentVO> getAllComments(String productId){
        int toId=Integer.parseInt(productId);
        float rat=0;
        List<Comment> comments=commentRepository.findByProductId(toId);
        List<CommentVO> commentVOS=new LinkedList<>();
        for(int i=0;i<comments.size();i++){
            rat+=comments.get(i).getRate();
            commentVOS.add(comments.get(i).toVO());
        }
        Product product=productRepository.findById(toId);
        if (product.getCommentNum()==0){
            product.setRate(0);
        }else {
            product.setRate(rat/product.getCommentNum());
        }

        productRepository.save(product);
        return commentVOS;
    };
}
