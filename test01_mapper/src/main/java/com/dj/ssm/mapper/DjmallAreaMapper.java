package com.dj.ssm.mapper;

import com.dj.ssm.pojo.DjmallArea;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface DjmallAreaMapper {

    List<DjmallArea> findDjmallAreaByParentId(Integer areaParentId) throws DataAccessException;
}
