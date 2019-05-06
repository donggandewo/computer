package com.zzh.dao;

import com.zzh.entity.Product;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProductDao extends Mapper<Product> {
    public List<Product> selectByCondition(Product product);
    public Product selectProduct(int productId);
}
