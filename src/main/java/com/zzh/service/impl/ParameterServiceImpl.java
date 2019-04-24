package com.zzh.service.impl;

import com.zzh.dao.ParameterDao;

import com.zzh.entity.Parameter;
import com.zzh.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("parameterService")
public class ParameterServiceImpl implements ParameterService {
    @Autowired
    private ParameterDao parameterDao;

    @Override
    public Parameter selectDetails(int details, String condition) {
        return parameterDao.selectDetails(details, condition);
    }

    @Override
    public List<Parameter> selectByVal(String val) {
        List<Parameter> parameters = parameterDao.selectByVal(val);
        return parameters;
    }

    @Override
    public List<Parameter> selectByDDR(int minddr, int maxddr) {
        return parameterDao.selectByDDR(minddr, maxddr);
    }
}
