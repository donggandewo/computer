package com.zzh.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;

@Data
@NoArgsConstructor
public class Details {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer detailsId;
    private Integer categoryId;
    private String desc;
    private Product product;
}
