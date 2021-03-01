package com.tuhui.alarmpush.services;

import com.tuhui.alarmpush.domain.SysAreaPolice;

import java.util.List;

/**
 * @author liy
 * @date 2021/2/26 14:49
 */
public interface SysAreaPoliceService {

    /**
     * 根据警号获取userOpenId
     * @return
     */
    String selectUserOpenIdByMobile(String token, String mobile);

    /**
     * 查询数据库所有人员信息
     * @param sysAreaPolice
     * @return
     */
    List<SysAreaPolice> selectSysAreaPoliceList(SysAreaPolice sysAreaPolice);

    /**
     * 批量更新userOpenId
     * @param sysAreaPoliceList
     * @return
     */
    int batchUpdateSysAreaPoliceById(List<SysAreaPolice> sysAreaPoliceList);

    /**
     * 根据警号更改userOpenId
     * @param mobile
     * @param userOpenId
     * @return
     */
    int updateUserOpenIdByMobile(String mobile, String userOpenId);

}
