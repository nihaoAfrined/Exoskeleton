package com.wxy.exoskeleton.service;

import com.wxy.exoskeleton.mapper.DetailedInfoMapper;
import com.wxy.exoskeleton.mapper.TreatmentRecordMapper;
import com.wxy.exoskeleton.model.AllInfo;
import com.wxy.exoskeleton.model.DetailedInfo;
import com.wxy.exoskeleton.model.RespPageBean;
import com.wxy.exoskeleton.model.TreatmentRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DetailedInfoService {

    @Autowired
    DetailedInfoMapper dInfoMapper;

    @Autowired
    TreatmentRecordMapper tRecordMapper;

    public RespPageBean getTRecordsByPage(Integer page, Integer size, TreatmentRecord tRecord, Date[] beginDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<TreatmentRecord> data = tRecordMapper.getTRecordsByPage(page, size, tRecord, beginDateScope);
        Long total = tRecordMapper.getTotal(tRecord, beginDateScope);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    public DetailedInfo getDInfoByUid(Integer uid) {
        return dInfoMapper.getDInfoByUid(uid);
    }

    public Integer addDInfo(AllInfo allInfo) {
        return dInfoMapper.insertDInfoSelective(allInfo);
    }

    public Integer updateDInfo(AllInfo allInfo) {
        return dInfoMapper.updateDInfoByPrimaryKeySelective(allInfo);
    }

    public Integer deleteDInfo(Integer id) {
        return dInfoMapper.deleteDInfo(id);
    }

    public Integer addTRecord(TreatmentRecord tRecord) {
        return tRecordMapper.insertTRecordSelective(tRecord);
    }

    public Integer deleteTRecord(Integer id) {
        return tRecordMapper.deleteTRecordByPrimaryKey(id);
    }

    public Integer updateTRecord(TreatmentRecord tRecord) {
        return tRecordMapper.updateByPrimaryKeySelective(tRecord);
    }


}
