package com.zzh.service;


import com.zzh.entity.Parameter;


import java.util.List;


public interface ParameterService {
    public Parameter selectDetails(int details, String condition);

    public List<Parameter> selectByVal(String val);
}