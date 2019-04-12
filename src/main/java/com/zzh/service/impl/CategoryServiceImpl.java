package com.zzh.service.impl;

import com.zzh.dao.CategoryDao;
import com.zzh.entity.Category;
import com.zzh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> selectAll() {
        return categoryDao.selectAll();
    }
}
