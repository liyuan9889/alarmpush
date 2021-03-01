package com.tuhui.alarmpush.mapper;

import com.tuhui.alarmpush.domain.Face;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface FaceMapper {

    Face selectInfoById(int id);
}
