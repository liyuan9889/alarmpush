package com.tuhui.alarmpush.services.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tuhui.alarmpush.constant.UrlConstant;
import com.tuhui.alarmpush.domain.SysAreaPolice;
import com.tuhui.alarmpush.mapper.SysAreaPoliceMapper;
import com.tuhui.alarmpush.services.SysAreaPoliceService;
import com.tuhui.alarmpush.utils.RestTemplateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liy
 * @date 2021/2/26 14:50
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class SysAreaPoliceImpl implements SysAreaPoliceService {

    private final SysAreaPoliceMapper sysAreaPoliceMapper;

    @Override
    public String selectUserOpenIdByMobile(String token, String mobile) {
        String userOpenId = "";
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(mobile);
        jsonObject.put("keyList", jsonArray);
        jsonObject.put("keyType", "police_no");
        String result = RestTemplateUtil.postJsonResponse(UrlConstant.MEMBER_INFO_LIST_URL.replace("CORP_TOKEN", token), jsonObject);
        log.info("获取人员基本信息：{}", result);
        if (StringUtils.isNotEmpty(result)) {
            JSONArray jArray = JSONObject.parseObject(result).getJSONArray("userInfoList");
            if (jArray.size() == 0){
                return userOpenId;
            }
            userOpenId = jArray.getJSONObject(0).getJSONObject("userInfo").getString("userOpenId");
        }
        return userOpenId;
    }

    @Override
    public List<SysAreaPolice> selectSysAreaPoliceList(SysAreaPolice sysAreaPolice) {
        return sysAreaPoliceMapper.selectSysAreaPoliceList(sysAreaPolice);
    }

    @Override
    public int batchUpdateSysAreaPoliceById(List<SysAreaPolice> sysAreaPoliceList) {
        return sysAreaPoliceMapper.batchUpdateSysAreaPoliceById(sysAreaPoliceList);
    }

    @Override
    public int updateUserOpenIdByMobile(String mobile, String userOpenId) {
        return sysAreaPoliceMapper.updateUserOpenIdByMobile(mobile, userOpenId);
    }

}
