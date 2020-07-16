package com.dj.ssm.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Token {

    private Integer id;

    private Integer userId;

    private String token;

    private Date validTime;
}
