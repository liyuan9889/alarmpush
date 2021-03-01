package com.tuhui.alarmpush.services;


import java.util.List;

public interface PoliceService  {

    List<String> selectPoliceListByAreaId(int areaId);

    List<String> selectPoliceListByIsR();

}
