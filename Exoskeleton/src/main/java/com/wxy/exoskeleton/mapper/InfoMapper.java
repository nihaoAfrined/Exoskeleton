package com.wxy.exoskeleton.mapper;

import com.wxy.exoskeleton.model.AllInfo;
import com.wxy.exoskeleton.model.Info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InfoMapper {

    List<AllInfo> getInfoByPage(Integer page, Integer size, Info info);

    Long getTotal(@Param(value = "info") Info info);

    List<Info> findInfoByLength(Double length);

    Integer updateInfoByPrimaryKeySelective(Info info);

    Integer addInfo(@Param(value = "allInfo") AllInfo allInfo);

    Integer addInfos(List<AllInfo> list);

    Integer deleteInfoByPrimaryKey(Integer id);
}
