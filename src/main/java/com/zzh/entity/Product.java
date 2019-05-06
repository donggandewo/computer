package com.zzh.entity;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "product")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer productId;
    private String productName;
    private Double oldPrice;
    private Double newPrice;
    private Category category;
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addTime;
    private Brands brands;
    private String description;
    private Double recommend;
    private Details details;
    private Double performance;
    private Double media;
    private Integer mediaNum;
    private Integer sales;
}
