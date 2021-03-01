package com.tuhui.alarmpush.services;

import com.tuhui.alarmpush.domain.Official;
import java.util.List;

/**
 * @author liy
 * @date 2021/3/1 9:19
 */
public interface PushMsgService {

    int testPushMessage(String filePath, String userOpenId);

    int pushMessage(Official official);



}
