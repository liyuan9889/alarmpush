package com.tuhui.alarmpush.constant;

/**
 * @author liy
 * @date 2021/2/25 9:53
 */
public class UrlConstant {

    public static final String BASE_URL = "http://jyt.gaj.nkg.js:18100";

    // 获取应用凭证
    public static final String APP_AUTH_URL = BASE_URL + "/auth/cgi-bin/token?appCode=MY_APP_CODE&appKey=MY_APP_KEY&appSecret=MY_APP_SECRET&grant_type=CLIENT_CREDENTIAL&openOrgDomain=OPEN_ORG_DOMAIN";
    // 资源上传
    public static final String UPLOAD_URL = BASE_URL + "/open/corp/resource/upload";
    // 批量获取人员基本信息
    public static final String MEMBER_INFO_LIST_URL = BASE_URL + "/open/corp/struct/member/info/list?corp_token=CORP_TOKEN";
    // 推送图片信息
    public static final String PUSH_PICTURE_INFO = BASE_URL + "/open/corp/message/send?corp_token=CORP_TOKEN";


}
