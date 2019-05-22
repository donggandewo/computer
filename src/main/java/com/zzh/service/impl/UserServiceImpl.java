package com.zzh.service.impl;

import com.zzh.dao.UserDao;
import com.zzh.entity.User;
import com.zzh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User login(User user) {
        User login = userDao.login(user);
        return login;
    }

    @Override
    public void regist(User user) {
        userDao.insert(user);
    }
}
