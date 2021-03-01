package com.tuhui.alarmpush.services.impl;

import com.tuhui.alarmpush.services.FaceService;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FaceServiceImpl implements FaceService {


    public static void main(String[] args) {
        String f = "C:\\Users\\Administrator\\Desktop\\45capt.jpg";
        File file = new File(f);
        System.out.println(file.isFile());
    }
}
