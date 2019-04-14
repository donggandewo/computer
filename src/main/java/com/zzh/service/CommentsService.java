package com.zzh.service;

import com.zzh.entity.Comment;

import java.util.List;

public interface CommentsService {
    public List<Comment> selectByProduct(int productId);
}
