package com.tuhui.alarmpush.domain;

import com.tuhui.alarmpush.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class AccessToken {

    private volatile static AccessToken instance;
    private volatile static Map<String,String> map = new HashMap<>();

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    private AccessToken(){}

    public static AccessToken getInstance() {
        if(instance == null) {
            synchronized(AccessToken.class) {
                if(instance == null) {
                    instance = new AccessToken();
                }
            }
        }
        return instance;
    }

    @Autowired
    private AuthService authService;
    @PostConstruct
    public void init(){
        instance = this;
        instance.authService = this.authService;
    }

    public String getAccessToken() {
        String result = null;
        AccessToken singleton = AccessToken.getInstance();
        Map<String,String> map = singleton.getMap();
        String time = map.get("time");
        String accessToken = map.get("access_token");

        Long currentDate = new Date().getTime();
        if(accessToken != null && time != null && currentDate-Long.parseLong(time) < 7000*1000) {
            result = accessToken;
        } else {
            String token = authService.getToken();
            Map<String, String> newMap = new HashMap<>();
            newMap.put("time", String.valueOf(new Date().getTime()));
            newMap.put("access_token", token);
            singleton.setMap(newMap);
            result = token;
        }

        return result;
    }

}
