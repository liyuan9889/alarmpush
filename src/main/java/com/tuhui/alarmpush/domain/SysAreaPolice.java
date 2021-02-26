package com.tuhui.alarmpush.domain;

import lombok.Data;

/**
 * @author liy
 * @date 2021/2/26 14:30
 */
@Data
public class SysAreaPolice {

    private Long id;
    private String name;
    private String mobile;
    private String delFlag;
    private Integer areaId;
    private String isRecvAll;
    private String userOpenId;

    public SysAreaPolice() {
    }

    public SysAreaPolice(Long id, String name, String mobile, String delFlag, Integer areaId, String isRecvAll, String userOpenId) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.delFlag = delFlag;
        this.areaId = areaId;
        this.isRecvAll = isRecvAll;
        this.userOpenId = userOpenId;
    }


}
