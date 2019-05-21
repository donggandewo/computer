package com.zzh.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class Cart {
    private LinkedHashMap<Integer, Product> map = new LinkedHashMap<>();
    private double price;
    private double price2; //原价
}
