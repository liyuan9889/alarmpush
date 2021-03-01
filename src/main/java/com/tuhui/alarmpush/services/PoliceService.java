package com.tuhui.alarmpush.services;


import com.tuhui.alarmpush.domain.Police;

import java.util.List;

public interface PoliceService  {

    List<Police> selectPoliceListByAreaId(int areaId);

    List<Police> selectPoliceListByIsR();

    List<Police> selectMobileByPoliceCodes(String policeCodes);

}
