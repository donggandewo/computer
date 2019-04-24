package com.zzh.service;

import com.zzh.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> selectAll(int pageIndex,int pageSize);

    public List<Product> selectByCondition(Product product);

    public Product selectOne(int productId);
}
