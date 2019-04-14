package com.zzh.dao;

import com.zzh.entity.Comment;

import java.util.List;

public interface CommentsDao {
    public List<Comment> selectByProduct(int productId);
}
