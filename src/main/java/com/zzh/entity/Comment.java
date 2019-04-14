package com.zzh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
public class Comment {
    @Id
    @KeySql(useGeneratedKeys = true)
    private int commentId;
    private String comments;
    private int levels;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date commentTime;
}
