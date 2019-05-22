package com.zzh.service;

import com.zzh.entity.User;

public interface UserService {
    public User login(User user);

    public void regist(User user);
}
