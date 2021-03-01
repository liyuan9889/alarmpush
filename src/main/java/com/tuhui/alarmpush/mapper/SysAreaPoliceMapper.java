package com.tuhui.alarmpush.mapper;

import com.tuhui.alarmpush.domain.SysAreaPolice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liy
 * @date 2021/2/25 10:14
 */
public interface SysAreaPoliceMapper {

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
    int updateUserOpenIdByMobile(@Param("mobile") String mobile, @Param("userOpenId") String userOpenId);

}
