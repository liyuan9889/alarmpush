package com.tuhui.alarmpush.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Slf4j
public class RestTemplateUtil {

    /**
     * Get方式请求
     * @param url
     * @return
     */
    public static String getResponse(String url) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(100000);// 设置超时
        requestFactory.setReadTimeout(100000);

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        String response = restTemplate.getForEntity(url, String.class).getBody();
        return response;
    }

    /**
     * POST方式表单请求
     * @param url
     * @return
     */
    public static String postFormResponse(String url, MultiValueMap<String, String> map){
        //复杂构造函数的使用
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(100000);// 设置超时
        requestFactory.setReadTimeout(100000);

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
        requestFactory.setConnectTimeout(100000);// 设置超时
        requestFactory.setReadTimeout(100000);
        String result = "";
        try {
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            HttpHeaders headers = new HttpHeaders();
            headers.add("accept", "*/*");
            headers.add("connection", "Keep-Alive");
            headers.add("Content-Type", "application/json");
            HttpEntity<String> formEntity = new HttpEntity<>(jsonObject.toString(), headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, formEntity, String.class);
            result = response.getBody();
        }  catch (Exception e) {
            log.error("POST方式JSON请求发生异常:{}", e);
        }

        return result;
    }

    /**
     * 文件上传
     * @param url
     * @param token
     * @param filePath
     * @return
     */
    public static String uploadFile(String url, String token, String filePath) {
        //复杂构造函数的使用
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(100000);// 设置超时
        requestFactory.setReadTimeout(100000);
        String result = "";
        try {
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.add("accept", "*/*");
            headers.add("connection", "Keep-Alive");
            FileSystemResource resource = new FileSystemResource(new File(filePath));
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("corp_token", token);
            param.add("resourceType", 1);
            param.add("resource", resource);
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(param, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
            result = response.getBody();
        } catch (Exception e) {
            log.error("上传文件发生异常:{}", e);
        }

        return result;
    }

}
