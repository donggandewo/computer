package com.zzh.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Details {
    private int detailsId;
    private int categoryId;
    private String desc;
}
