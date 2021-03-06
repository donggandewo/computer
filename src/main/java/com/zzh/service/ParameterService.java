package com.zzh.service;


import com.zzh.entity.Parameter;


import java.util.List;


public interface ParameterService {
    public Parameter selectDetails(int details, String condition);

    public List<Parameter> selectByVal(Parameter parameter, Integer min, Integer max);

    public List<Parameter> selectByDDR(int minddr, int maxddr);

    public Parameter selectPower(Parameter parameter);
}
