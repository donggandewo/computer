package com.zzh.entity;

import lombok.Data;


import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;
@Data
@Table(name = "category")
public class Category {
    @Id
    private int categoryId;
    private String categoryName;
    private int parentsId;
    @Transient
    private List<Category> categoryList;

}
