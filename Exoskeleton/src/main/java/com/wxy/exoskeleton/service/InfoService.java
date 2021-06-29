package com.wxy.exoskeleton.service;

import com.wxy.exoskeleton.mapper.InfoMapper;
import com.wxy.exoskeleton.model.AllInfo;
import com.wxy.exoskeleton.model.Info;
import com.wxy.exoskeleton.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class InfoService {

    @Autowired
    InfoMapper infoMapper;

    @Autowired
    DetailedInfoService detailedInfoService;


    public RespPageBean getInfoByPage(Integer page, Integer size, Info info) {
        if(page != null && size != null) {
            page = (page - 1) * size;
        }
        List<AllInfo> data = infoMapper.getInfoByPage(page, size, info);
        Long total = infoMapper.getTotal(info);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

//    public List<Info> findInfoByPage(Integer page, Integer size) {
//        List<Info> data = infoMapper.findInfoByPage(page, size);
//        return  data;
//    }

    public List<Info> findInfoByLength(Double length) {
        List<Info> data = infoMapper.findInfoByLength(length);
        return  data;
    }

    @Transactional
    public Integer addInfo(AllInfo allInfo) {

        if(infoMapper.addInfo(allInfo) + detailedInfoService.addDInfo(allInfo) == 2) {
            return 1;
        }

        return 0;
    }

    public Integer updateInfo(AllInfo allInfo) {
        return infoMapper.updateInfoByPrimaryKeySelective(allInfo);
    }

    @Transactional
    public Integer addInfos(List<AllInfo> list) {
        return (infoMapper.addInfos(list) + detailedInfoService.addDInfos(list));
    }

    public Integer deleteInfoByPrimaryKey(Integer id) {
        if(infoMapper.deleteInfoByPrimaryKey(id) + detailedInfoService.deleteDInfo(id) == 2) {
            return 1;
        }
        return 0;
    }


}
