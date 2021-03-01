package com.tuhui.alarmpush.services.impl;


import com.tuhui.alarmpush.domain.Alarm;
import com.tuhui.alarmpush.mapper.AlarmMapper;
import com.tuhui.alarmpush.services.AlarmService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    private AlarmMapper alarmMapper;

    @Override
    public Alarm selectsCodeByImgName(String largeImg) {
        if(StringUtils.isNotEmpty(largeImg)){
            Alarm alarm = new Alarm();
            alarm.setLargeImageFileName(largeImg);
            alarm = alarmMapper.selectsCodeByImgName(alarm);
            if(alarm != null){
                return alarm;
            }else{
                log.error("查询为空");
                return null;
            }
        }
        log.error("largeImg：参数为空");
        return null;
    }

    @Override
    public int updateDelFlag(int id) {
        return alarmMapper.updateDelFlag(id);
    }
}
