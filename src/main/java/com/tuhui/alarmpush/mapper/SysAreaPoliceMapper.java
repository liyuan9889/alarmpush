package com.tuhui.alarmpush.mapper;

import com.tuhui.alarmpush.domain.SysAreaPolice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author liy
 * @date 2021/2/25 10:14
 */
public interface SysAreaPoliceMapper {

    List<SysAreaPolice> selectSysAreaPoliceList(SysAreaPolice sysAreaPolice);

    /**
     * 批量更新userOpenId
     * @param sysAreaPoliceList
     * @return
     */
    int batchUpdateSysAreaPoliceById(List<SysAreaPolice> sysAreaPoliceList);

}
