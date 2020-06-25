package com.cn.redis.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class UserEntity implements Serializable {
    private Long id;
    private String guid;
    private String name;
    private String age;
    private Date createTime;
}
