package com.zzh.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;

@Data
@NoArgsConstructor
public class Parameter {
    @Id
    @KeySql(useGeneratedKeys = true)
    private int parameterId;
    private Details details;
    private String parameterName;
    private String parameterVal; //参数信息
    private Integer parameterInt;
}
