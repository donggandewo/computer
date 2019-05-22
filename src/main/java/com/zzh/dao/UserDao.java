package com.zzh.dao;

import com.zzh.entity.User;
import tk.mybatis.mapper.common.Mapper;

public interface UserDao extends Mapper<User> {
    public User login(User user);
}
