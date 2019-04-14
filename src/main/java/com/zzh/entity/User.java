package com.zzh.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;

@Data
public class User {
    @Id
    @KeySql(useGeneratedKeys = true)
    private int userId;
    private String username;
    private String mail;
    private String phone;
}
