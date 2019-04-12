package com.zzh.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;
@Data
@Table(name = "category")
public class Category {
    @Id
    @KeySql(useGeneratedKeys = true)
    private int categoryId;
    private String categoryName;
    private int parentsId;
    @Transient
    private List<Category> categoryList;

}
