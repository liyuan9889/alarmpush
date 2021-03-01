package com.tuhui.alarmpush.services;

import com.tuhui.alarmpush.domain.Face;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FaceService {

    Face selectInfoById(int id);
}
