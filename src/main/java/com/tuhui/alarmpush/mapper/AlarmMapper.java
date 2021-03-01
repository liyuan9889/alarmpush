package com.tuhui.alarmpush.mapper;

import com.tuhui.alarmpush.domain.Alarm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlarmMapper {

    Alarm selectsCodeByImgName(Alarm alarm);

    int updateDelFlag(int id);
}
