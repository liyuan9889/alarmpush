package com.tuhui.alarmpush.domain;

import lombok.Data;

import java.util.List;

@Data
public class Official {

    private String smallImg; //小图

    private String largeImg;//大图

    private String faceImg;//证件照

    private List<String> policeList; //警员编号集合

}