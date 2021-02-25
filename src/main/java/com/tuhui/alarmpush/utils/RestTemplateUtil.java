package com.tuhui.alarmpush.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.nio.charset.StandardCharsets;

@Component
public class RestTemplateUtil {

    /**
     * Get方式请求
     * @param url
     * @return
     */
    public static String getResponse(String url) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(200000);// 设置超时
        requestFactory.setReadTimeout(200000);

        HttpsClientRequestFactory httpsClientRequestFactory = new HttpsClientRequestFactory();
        httpsClientRequestFactory.setConnectTimeout(20000);
        httpsClientRequestFactory.setReadTimeout(20000);

        RestTemplate restTemplate;
        int index = url.indexOf("https:");
        if (index != -1){
            restTemplate = new RestTemplate(httpsClientRequestFactory);
        } else {
            restTemplate = new RestTemplate(requestFactory);
        }

        String response = restTemplate.getForEntity(url, String.class).getBody();
        return response;
    }

    /**
     * POST方式表单请求
     * @param url
     * @return
     */
    public static String postResponse(String url, MultiValueMap<String, String> map){
        //复杂构造函数的使用
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(200000);// 设置超时
        requestFactory.setReadTimeout(200000);

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        HttpHeaders headers = new HttpHeaders();
        // 设置请求的格式类型
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return response.getBody();
    }

    /**
     * POST方式JSON请求
     * @param url
     * @param jsonObject
     * @return
     */
    public static String postJsonResponse(String url, JSONObject jsonObject){
        //复杂构造函数的使用
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(200000);// 设置超时
        requestFactory.setReadTimeout(200000);

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        HttpHeaders headers = new HttpHeaders();
        headers.add("accept", "*/*");
        headers.add("connection", "Keep-Alive");
        headers.add("Content-Type", "application/json");
        HttpEntity<String> formEntity = new HttpEntity<>(jsonObject.toString(), headers);
        String response = restTemplate.exchange(url, HttpMethod.POST, formEntity, String.class).getBody();
        return response;
    }

}
