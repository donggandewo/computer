package com.zzh.service.impl;

import com.zzh.dao.DetailsDao;
import com.zzh.entity.Details;
import com.zzh.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("detailsService")
public class DetailsServiceImpl implements DetailsService {
    @Autowired
    private DetailsDao detailsDao;

    @Override
    public Details selectByProduct(int productId) {
        return detailsDao.selectByProduct(productId);
    }
}
