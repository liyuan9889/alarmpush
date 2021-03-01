package com.tuhui.alarmpush.services.impl;

import com.tuhui.alarmpush.domain.Face;
import com.tuhui.alarmpush.domain.Police;
import com.tuhui.alarmpush.mapper.FaceMapper;
import com.tuhui.alarmpush.services.FaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Slf4j
@Service
public class FaceServiceImpl implements FaceService {

    @Autowired
    private FaceMapper faceMapper;

    @Override
    public Face selectInfoById(int id) {
        Face face = faceMapper.selectInfoById(id);
        if(face != null){
            return face;
        }
        return null;
    }
}
