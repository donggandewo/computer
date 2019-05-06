package com.zzh.dao;

import com.zzh.entity.Product;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProductDao extends Mapper<Product> {
    public List<Product> selectByCondition(@Param("product") Product product, @Param("max") Double max, @Param("min") Double min);
    public Product selectProduct(int productId);
}
