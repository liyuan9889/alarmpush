package com.tuhui.alarmpush.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tuhui.alarmpush.constant.UrlConstant;
import com.tuhui.alarmpush.services.FileUploadService;
import com.tuhui.alarmpush.utils.RestTemplateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author liy
 * @date 2021/2/25 16:14
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

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

    /**
     * 单文件上传文件
     * @param token
     * @param filePath
     * @return 返回空值代表上传失败，有值则表示成功
     */
    @Override
    public String uploadFile(String token, String filePath) {
        String resourceId = "";
        String result = RestTemplateUtil.uploadFile(UrlConstant.UPLOAD_URL, token, filePath);
        if (StringUtils.isNotEmpty(result)) {
            JSONObject jsonObject = JSON.parseObject(result);
            int errCode = (int) jsonObject.get("errcode");
            if (errCode == 0) {
                resourceId = jsonObject.getJSONObject("result").getString("resourceId");
            }
        }
        return resourceId;
    }

}
