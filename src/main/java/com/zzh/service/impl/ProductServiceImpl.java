package com.zzh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzh.dao.ProductDao;
import com.zzh.entity.Product;
import com.zzh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public List<Product> selectAll(int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        PageInfo<Product> pageInfo=new PageInfo<>(productDao.selectAll());
        return pageInfo.getList();
    }

    @Override
    public List<Product> selectByCondition(Product product) {
        return productDao.selectByCondition(product, null, null);
    }

    @Override
    public List<Product> selectByCondition(Product product, Double max, Double min) {
        return productDao.selectByCondition(product, max, min);
    }

    @Override
    public Product selectOne(int productId) {
        Product product = productDao.selectProduct(productId);
        return product;
    }

    @Override
    public void insert(Product product) {
        productDao.insert(product);
    }

    @Override
    public void update(Product product) {
        productDao.updateByPrimaryKey(product);
    }
}
