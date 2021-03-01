package com.tuhui.alarmpush.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Police implements Serializable {
    private  long     id;
    private  String    name;
    private  String    mobile;
    private  String    del_flag;
    private  int       area_id;
    private  String    is_recv_all;
    private  String  user_open_id ;
}