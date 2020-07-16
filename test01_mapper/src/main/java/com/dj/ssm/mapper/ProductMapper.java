package com.dj.ssm.mapper;

import com.dj.ssm.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ProductMapper {

    List<Product> findAll(@Param("isDel") Integer isDel) throws DataAccessException;

    void addProduct(Product product) throws  DataAccessException;

    Product findProductByProName(String proName) throws DataAccessException;

    Product findProductById(Integer id) throws DataAccessException;

    void updateProduct(Product product) throws  DataAccessException;
}
