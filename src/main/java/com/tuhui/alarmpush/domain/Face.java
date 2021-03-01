package com.tuhui.alarmpush.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Face implements Serializable {
    private  int   id;
    private  Date create_date;
    private  String  del_flag;
    private  Date update_date;
    private  String  remarks;
    private  String  card_num;
    private  String  face_name;
    private  String  face_sex;
    private  int      height;
    private  String  place_origin;
    private  int      weight;
    private  int   create_by;
    private  int   update_by;
    private  String  face_type;
    private  String  area;
    private  String  arrest_bonus;
    private  String  arrest_code;
    private  String  birth_day;
    private  String  contact;
    private  String  face_name_ever;
    private  String  phone;
    private  String  s_code;
}
