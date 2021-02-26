package com.tuhui.alarmpush.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tuhui.alarmpush.constant.UrlConstant;
import com.tuhui.alarmpush.services.AuthService;
import com.tuhui.alarmpush.utils.RestTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author liy
 * @date 2021/2/25 15:21
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Value("${halo.app_code}")
    private String app_code;
    @Value("${halo.app_key}")
    private String app_key;
    @Value("${halo.app_secret}")
    private String app_secret;
    @Value("${halo.grant_type}")
    private String grant_type;
    @Value("${halo.openOrgDomain}")
    private String openOrgDomain;

    @Override
    public String getToken() {
        String url = UrlConstant.APP_AUTH_URL.replace("MY_APP_CODE", app_code).replace("MY_APP_KEY", app_key)
                .replace("MY_APP_SECRET", app_secret).replace("CLIENT_CREDENTIAL", grant_type).replace("OPEN_ORG_DOMAIN", openOrgDomain);
        String jsonStr = RestTemplateUtil.getResponse(url);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String token = (String) jsonObject.get("access_token");
        return token;
    }

}
