package com.zzh.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Parameter {
    private int parameterId;
    private Details details;
    private String parameterName;
    private String parameterVal; //参数信息
}
