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
public class ProductDaoImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public List<Product> selectAll(int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        PageInfo<Product> pageInfo=new PageInfo<>(productDao.selectAll());
        return pageInfo.getList();
    }
}
