package com.zzh.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
@Data
@NoArgsConstructor
@Table(name = "brands")
public class Brands {
    @Id
    @KeySql(useGeneratedKeys = true)
    private int brandsId;
    private String brandsName;
    private String brandPic;
}
