package com.dj.ssm.service.impl;

import com.dj.ssm.mapper.ProductMapper;
import com.dj.ssm.pojo.Product;
import com.dj.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findAll(Integer isDel) throws Exception {
        return productMapper.findAll(isDel);
    }

    @Override
    public void addProduct(Product product) throws Exception {
        productMapper.addProduct(product);
    }

    @Override
    public Product findProductByProName(String proName) throws Exception {
        return productMapper.findProductByProName(proName);
    }

    @Override
    public Product findProductById(Integer id) throws Exception {
        return productMapper.findProductById(id);
    }

    @Override
    public void updateProduct(Product product) throws Exception {
        productMapper.updateProduct(product);
    }
}
