package com.zzh.service;

import com.zzh.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> selectAll(int pageIndex,int pageSize);

    public List<Product> selectByCondition(Product product);

    public List<Product> selectByCondition(Product product, Double max, Double min);
    public Product selectOne(int productId);

    public void insert(Product product);

    public void update(Product product);
}
