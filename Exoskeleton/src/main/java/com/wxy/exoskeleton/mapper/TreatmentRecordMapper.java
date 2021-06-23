package com.wxy.exoskeleton.mapper;

import com.wxy.exoskeleton.model.TreatmentRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface TreatmentRecordMapper {

    List<TreatmentRecord> getTRecordsByPage(Integer uid, Integer page, Integer size, TreatmentRecord tRecord);

    Long getTotal(Integer uid, TreatmentRecord tRecord);

    Integer insertTRecordSelective(TreatmentRecord tRecord);

    Integer deleteTRecordByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(TreatmentRecord tRecord);
}
