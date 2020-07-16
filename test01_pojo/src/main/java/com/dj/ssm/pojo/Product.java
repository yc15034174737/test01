package com.dj.ssm.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Product {

    private Integer id;

    private String proName;

    private Integer proCount;

    private Integer userId;

    private Integer isDel;


}
