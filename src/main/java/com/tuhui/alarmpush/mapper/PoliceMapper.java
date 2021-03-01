package com.tuhui.alarmpush.mapper;

import com.tuhui.alarmpush.domain.Police;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PoliceMapper {


    List<String> selectPoliceListByAreaId(int areaId);

    List<String> selectPoliceListByIsR();
}
