package com.dj.ssm.service.impl;

import com.dj.ssm.mapper.DjmallAreaMapper;
import com.dj.ssm.pojo.DjmallArea;
import com.dj.ssm.service.DjmallAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DjmallAreaServiceImpl implements DjmallAreaService {

    @Autowired
    private DjmallAreaMapper djmallAreaMapper;

    @Override
    public List<DjmallArea> findDjmallAreaByParentId(Integer areaParentId) throws Exception {
        return djmallAreaMapper.findDjmallAreaByParentId(areaParentId);
    }
}
