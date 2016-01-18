package com.quliantrip.qulian.net.constant;

/**
 * 定义网络请求的常量
 * Created by yuly on 2015/11/27.
 */
public class HttpConstants {
    /**
     * 线上服务器地址
     */
    public static final String HOST_ADDR_ROOT_NET = "http://www.quliantrip.com/mapi/index.php";

    /**
     * 本地服务器地址
     */
    public static final String HOST_ADDR_ROOT_LOCAL = "http://www.quliantrip.com/mapi/index.php";

    /**
     * 加载webView的连接路径
     */
    public static final String WEBVIEW_ROOT = "http://www.quliantrip.com/wap/index.php";

    /**
     * 本地v1服务器
     */
    public static final String HOST_ADDR_ROOT_LOCAL_V1 = "http://rest.qulian.com/v1/";
    /**
     * 手机号重复验证
     */
    public static final String CHECK_MOBILE_NUMBER = HOST_ADDR_ROOT_LOCAL_V1+"user/mobile";
    //手机号注册
    public static final String MOBILE_REGISTER = HOST_ADDR_ROOT_LOCAL_V1+"user/psignup";

    //用户登录
    public static final String USER_LOGON = HOST_ADDR_ROOT_LOCAL_V1+"user/login";



}
