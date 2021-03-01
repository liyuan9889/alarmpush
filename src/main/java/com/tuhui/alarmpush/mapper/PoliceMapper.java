package com.tuhui.alarmpush.mapper;

import com.tuhui.alarmpush.domain.Police;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PoliceMapper {


    List<Police> selectPoliceListByAreaId(int areaId);

    List<Police> selectPoliceListByIsR();

    List<Police> selectMobileByPoliceCodes(String polices);
}
