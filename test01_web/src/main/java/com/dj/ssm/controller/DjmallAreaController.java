package com.dj.ssm.controller;

import com.dj.ssm.mapper.DjmallAreaMapper;
import com.dj.ssm.pojo.DjmallArea;
import com.dj.ssm.pojo.ResultModel;
import com.dj.ssm.service.DjmallAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/djmallArea/")
@RestController
public class DjmallAreaController {

    @Autowired
    private DjmallAreaService djmallAreaService;


    @RequestMapping("ztreeShow")
    public List<DjmallArea> ztreeShow(Integer id){
        try {
            List<DjmallArea> citys = djmallAreaService.findDjmallAreaByParentId(id == null? 0:id);
            for (DjmallArea city : citys) {
                List<DjmallArea> cityList = djmallAreaService.findDjmallAreaByParentId(city.getId());
                city.setIsParent(false);
                if(null != cityList && cityList.size() > 0) {
                    city.setIsParent(true);
                }
            }
            return citys;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }
}
