package com.dj.ssm.service;

import com.dj.ssm.pojo.DjmallArea;

import java.util.List;

public interface DjmallAreaService {

    List<DjmallArea> findDjmallAreaByParentId(Integer areaParentId) throws Exception;
}
