package com.zzh.dao;

import com.zzh.entity.Details;

public interface DetailsDao {
    public Details selectByProduct(int productId);
}
