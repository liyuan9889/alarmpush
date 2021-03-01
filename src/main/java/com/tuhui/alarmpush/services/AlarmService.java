package com.tuhui.alarmpush.services;

import com.tuhui.alarmpush.domain.Alarm;

public interface AlarmService {

    Alarm selectsCodeByImgName(String largeImg) ;

    int updateDelFlag(int id);
}
