package com.zzh.service.impl;

import com.zzh.dao.CommentsDao;
import com.zzh.entity.Comment;
import com.zzh.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("commentsService")
@Transactional
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentsDao commentsDao;
    @Override
    public List<Comment> selectByProduct(int productId) {

        return commentsDao.selectByProduct(productId);
    }
}
