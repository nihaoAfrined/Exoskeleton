package com.wxy.exoskeleton.mapper;

import com.wxy.exoskeleton.model.AllInfo;
import com.wxy.exoskeleton.model.DetailedInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface DetailedInfoMapper {


    Integer insertDInfoSelective(AllInfo allInfo);

    Integer updateDInfoByPrimaryKeySelective(AllInfo allInfo);

    Integer deleteDInfo(Integer id);

    DetailedInfo getDInfoByUid(Integer uid);

    AllInfo getAInfoByUid(Integer uid);

    Integer addDInfos(List<AllInfo> list);


}
