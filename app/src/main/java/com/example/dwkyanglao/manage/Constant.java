package com.example.dwkyanglao.manage;

import com.example.dwkyanglao.activity.model.LoginModel;

/**
 * 项目通用常量
 */
public class Constant {

    public static String TCP_HOST_NAMENEW = "http://dev.bewatec.com.cn:28000";
    public static String URL_LOGIN = TCP_HOST_NAMENEW + "/api/Auth/login";
    public static String URL_TEST = TCP_HOST_NAMENEW + "/api/Test/Test3";
    public static String URL_MOBELE = TCP_HOST_NAMENEW + "/api/SMS/mobile/";
    public static String URL_mobileLogin = TCP_HOST_NAMENEW + "/api/Auth/mobileLogin";
    public static String URL_passwordset = TCP_HOST_NAMENEW + "/api/User/password/set";
    public static String URL_Userinfo = TCP_HOST_NAMENEW + "/api/User/userInfo/set";
    public static String URL_mimalogin = TCP_HOST_NAMENEW + "/api/Auth/login";
    public static String URL_updpassword = TCP_HOST_NAMENEW + "/api/User/password/upd";
    public static String URL_getuser = TCP_HOST_NAMENEW + "/api/User";
    public static String URL_displayName = TCP_HOST_NAMENEW + "/api/User/displayName";
    public static String URL_DeviceInfo= TCP_HOST_NAMENEW + "/api/DeviceInfo";
    public static String URL_Devicelist= TCP_HOST_NAMENEW + "/api/Group/list";
    public static String URL_Group= TCP_HOST_NAMENEW + "/api/Group/";
    public static String URL_Groupxq= TCP_HOST_NAMENEW + "/api/Group";
    public static String URL_getota= TCP_HOST_NAMENEW + "/api/Ota/device-types/";
    public static String URL_upgrade= TCP_HOST_NAMENEW + "/api/Ota/upgrade";
    public static String URL_DeviceConfig= TCP_HOST_NAMENEW + "/api/DeviceConfig";
    public static String URL_statisticsCount= TCP_HOST_NAMENEW + "/api/Warn/statisticsCount";
    public static String URL_Warnlist= TCP_HOST_NAMENEW + "/api/Warn/list";

    public static String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6IjdlOGU3MjI4LWQ2OWMtNDA2NC04ZDcxLWM1ZTIyNGE4MWQzZiIsImV4cCI6MTcwODA0NjQwNywiaXNzIjoiYmV3YXRlYyIsImF1ZCI6ImJld2F0ZWMifQ.hB5hHDBPnvT5GpxU1hQ6dRtzdia95v2Q4p03IyXCA_I";
    public static String mqttToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IjdlOGU3MjI4LWQ2OWMtNDA2NC04ZDcxLWM1ZTIyNGE4MWQzZiIsImFjbCI6e30sImV4cCI6MTcwNzk2MjQ2Nn0.YT-E4sBu8MocK7TpTDH8O6LY_B-92R-ax5zEz3aq9eM";

    public static LoginModel myself;
    public static String TAG="pp";
}
