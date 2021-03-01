package com.tuhui.alarmpush.domain;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class Alarm  implements Serializable {

    private  int      id                   ;
    private  Timestamp comparisonDate       ;
    private  int      faceUserLibrary      ;
    private  int      faceImages           ;
    private  double   matchingDegree       ;
    private  int      comparisonFrom       ;
    private  String   deployDefenceCode    ;
    private  String   equipmentCode        ;
    private  String   deployDefenceName    ;
    private  int      areaId               ;
    private  String   equipmentName        ;
    private  String   largeImagePath       ;
    private  String   largeImageFileName   ;
    private  String   smallImagePath       ;
    private  String   smallImageFileName   ;
    private  String   videoUrl             ;
    private  String   faceImagePath        ;
    private  String   faceUserCode         ;
    private  int      isWarning            ;
    private  String   del_flag             ;
    private  String   handlePeople         ;
    private  int      isSeize              ;
    private  int      isConfirm            ;
    private  String   faceTypeName         ;
}
