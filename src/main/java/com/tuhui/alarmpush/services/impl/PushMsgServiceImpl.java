package com.tuhui.alarmpush.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tuhui.alarmpush.constant.UrlConstant;
import com.tuhui.alarmpush.domain.AccessToken;
import com.tuhui.alarmpush.domain.Official;
import com.tuhui.alarmpush.domain.Police;
import com.tuhui.alarmpush.services.FileUploadService;
import com.tuhui.alarmpush.services.PushMsgService;
import com.tuhui.alarmpush.services.SysAreaPoliceService;
import com.tuhui.alarmpush.utils.ExecutorServiceUtils;
import com.tuhui.alarmpush.utils.RestTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @author liy
 * @date 2021/3/1 9:20
 */
@Service
@Slf4j
public class PushMsgServiceImpl implements PushMsgService {

    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private SysAreaPoliceService policeService;

    @Override
    public int testPushMessage(String filePath, String userOpenId) {
        String token = AccessToken.getInstance().getAccessToken();
        String resourceId = fileUploadService.uploadFile(token, filePath);
        String url = UrlConstant.PUSH_PICTURE_INFO.replace("CORP_TOKEN", token);

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(userOpenId);
        jsonObject.put("userOpenIdList", jsonArray);
        jsonObject.put("msgType", "image");
        JSONObject jObject = new JSONObject();
        jObject.put("content", "测试消息");
        JSONArray jArray = new JSONArray();
        JSONObject object = new JSONObject();
        object.put("resourceId", resourceId);
        object.put("width", 800);
        object.put("height", 600);
        object.put("size", 10000);
        object.put("boriginal", true);
        jArray.add(object);
        jObject.put("imageItemList", jArray);
        jsonObject.put("image", jObject);

        log.info("推送消息Url：{}, 请求数据：{}", url, jsonObject);
        String result = RestTemplateUtil.postJsonResponse(url, jsonObject);
        JSONObject resultJSON = JSONObject.parseObject(result);
        int errCode = resultJSON.getIntValue("errcode");
        return errCode;
    }

    @Override
    public int pushMessage(Official official) {
        String token = AccessToken.getInstance().getAccessToken();
        String smallImgResourceId = fileUploadService.uploadFile(token, official.getSmallImg());
        String largeImgResourceId = fileUploadService.uploadFile(token, official.getLargeImg());
        String faceImgResourceId = fileUploadService.uploadFile(token, official.getFaceImg());
        String url = UrlConstant.PUSH_PICTURE_INFO.replace("CORP_TOKEN", token);

        List<Police> policeList = official.getPoliceList();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        policeList.forEach(police -> {
            if(StringUtils.isNotEmpty(police.getUser_open_id())) {
                jsonArray.add(police.getUser_open_id());
            } else {
                if (StringUtils.isNotEmpty(police.getMobile())){
                    String userOpenId = policeService.selectUserOpenIdByMobile(token, police.getMobile());
                    jsonArray.add(userOpenId);
                    updateUserOpenIdByMobile(police.getMobile(), userOpenId);
                }
            }
        });
        jsonObject.put("userOpenIdList", jsonArray);
        jsonObject.put("msgType", "image");
        JSONObject jObject = new JSONObject();
        jObject.put("content", official.getContent());
        JSONArray jArray = new JSONArray();

        JSONObject faceImgObj = new JSONObject();
        faceImgObj.put("resourceId", faceImgResourceId);
        faceImgObj.put("width", 800);
        faceImgObj.put("height", 600);
        faceImgObj.put("size", new File(official.getFaceImg()).length());
        faceImgObj.put("boriginal", true);
        jArray.add(faceImgObj);

        JSONObject smallImgObj = new JSONObject();
        smallImgObj.put("resourceId", smallImgResourceId);
        smallImgObj.put("width", 800);
        smallImgObj.put("height", 600);
        smallImgObj.put("size", new File(official.getSmallImg()).length());
        smallImgObj.put("boriginal", true);
        jArray.add(smallImgObj);

        JSONObject largeImgObj = new JSONObject();
        largeImgObj.put("resourceId", largeImgResourceId);
        largeImgObj.put("width", 800);
        largeImgObj.put("height", 600);
        largeImgObj.put("size", new File(official.getLargeImg()).length());
        largeImgObj.put("boriginal", true);
        jArray.add(largeImgObj);

        jObject.put("imageItemList", jArray);
        jsonObject.put("image", jObject);

        log.info("推送消息Url：{}, 请求数据：{}", url, jsonObject);
        String result = RestTemplateUtil.postJsonResponse(url, jsonObject);
        log.info("推送消息结果：{}", result);
        JSONObject resultJSON = JSONObject.parseObject(result);
        int errCode = resultJSON.getIntValue("errcode");
        return errCode;
    }

    public void updateUserOpenIdByMobile(String mobile, String userOpenId){
        ExecutorServiceUtils.getExecutorService().execute(new Runnable() {
            public void run() {
                policeService.updateUserOpenIdByMobile(mobile,userOpenId);
            }
        });
    }

}
