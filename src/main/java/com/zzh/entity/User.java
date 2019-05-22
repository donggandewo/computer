package com.zzh.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
public class User {
    @Id
    @KeySql(useGeneratedKeys = true)
    private int userId;
    private String username;
    private String mail;
    private String phone;

    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addTime;

    private int id = 0;
    private int status = 0;
    private String password;
}
