package com.dj.ssm.service;

import com.dj.ssm.pojo.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll(Integer isDel) throws Exception;

    void addProduct(Product product) throws  Exception;

    Product findProductByProName(String proName) throws Exception;

    Product findProductById(Integer id) throws Exception;

    void updateProduct(Product product) throws Exception;
}
