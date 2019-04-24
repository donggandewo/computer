package com.zzh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Table(name = "product")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @KeySql(useGeneratedKeys = true)
    private int productId;
    private String productName;
    private double oldPrice;
    private double newPrice;
    private Category category;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;
    private Brands brands;
    private String description;
    private int recommend;
    private Details details;

}
