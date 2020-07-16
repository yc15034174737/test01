package com.dj.ssm.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DjmallArea {

    private Integer id;

    private String areaName;

    private String areaPinyin;

    private Integer areaParentId;

    private Boolean isParent;
}
