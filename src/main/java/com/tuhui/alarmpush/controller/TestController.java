package com.tuhui.alarmpush.controller;

import com.alibaba.fastjson.JSONObject;
import com.tuhui.alarmpush.domain.AccessToken;
import com.tuhui.alarmpush.domain.AjaxResult;
import com.tuhui.alarmpush.domain.SysAreaPolice;
import com.tuhui.alarmpush.services.AuthService;
import com.tuhui.alarmpush.services.FileUploadService;
import com.tuhui.alarmpush.services.PushMsgService;
import com.tuhui.alarmpush.services.SysAreaPoliceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liy
 * @date 2021/2/25 10:15
 */
@RestController
public class TestController {

    @Autowired
    private PushMsgService pushMsgService;
    @Autowired
    private FileUploadService FileUploadService;
    @Autowired
    private SysAreaPoliceService sysAreaPoliceService;

    @GetMapping("/getToken")
    public Object getToken() {
        String token = AccessToken.getInstance().getAccessToken();
        return token;
    }

    @GetMapping("/uploadFile")
    public AjaxResult uploadFile(@RequestParam("filePath") String filePath) {
        String token = AccessToken.getInstance().getAccessToken();
        String resourceId = FileUploadService.uploadFile(token, filePath);
        return AjaxResult.success(resourceId);
    }


    @GetMapping("/getPolice")
    public AjaxResult getPolice() {
        String token = AccessToken.getInstance().getAccessToken();
        List<SysAreaPolice> sysAreaPoliceList = sysAreaPoliceService.selectSysAreaPoliceList(new SysAreaPolice());
        for (SysAreaPolice sysAreaPolice : sysAreaPoliceList) {
            String openId = sysAreaPoliceService.selectUserOpenIdByMobile(token, sysAreaPolice.getMobile());
            sysAreaPolice.setUserOpenId(openId);
        }

        sysAreaPoliceService.batchUpdateSysAreaPoliceById(sysAreaPoliceList);

        return AjaxResult.success(sysAreaPoliceList);
    }

    @GetMapping("/pushMsg")
    public AjaxResult pushMsg(@RequestParam("filePath") String filePath, @RequestParam("userOpenId") String userOpenId) {
        int code = pushMsgService.testPushMessage(filePath, userOpenId);
        return AjaxResult.success(code);
    }

}
