package com.zzh.dao;


import com.zzh.entity.Parameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ParameterDao {
    public Parameter selectDetails(@Param("details") int details, @Param("condition") String condition);

    public List<Parameter> selectByDDR(@Param("minddr") int minddr, @Param("maxddr") int maxddr);
    public List<Parameter> selectByVal(String val);
}
