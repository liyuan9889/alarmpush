package com.tuhui.alarmpush.services.impl;

import com.tuhui.alarmpush.domain.Police;
import com.tuhui.alarmpush.mapper.PoliceMapper;
import com.tuhui.alarmpush.services.PoliceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PoliceServiceImpl implements PoliceService {

    @Autowired
    private PoliceMapper policeMapper;


    @Override
    public List<Police> selectPoliceListByAreaId(int areaId) {
        if(areaId < 0){
            log.error("区域id为空，areaId：{}" ,areaId);
            return null;
        }
        List<Police> list =  policeMapper.selectPoliceListByAreaId(areaId);
        if(list != null && list.size() > 0){
            return list;
        }
        log.error("根据areaId查询的警员号集合为空!");
        return null;
    }

    @Override
    public List<Police> selectPoliceListByIsR() {
        List<Police> list =  policeMapper.selectPoliceListByIsR();
        if(list != null && list.size() > 0){
            return list;
        }
        log.error("查询不到is_recv_all 为1的警员号!");
        return null;
    }

    @Override
    public List<Police> selectMobileByPoliceCodes(String policeCodes) {
        List<Police> list = policeMapper.selectMobileByPoliceCodes(policeCodes);
        if(list != null && list.size() > 0){
            return list;
        }
        log.error("根据警员号，查询不到数据!");
        return null;
    }

}
