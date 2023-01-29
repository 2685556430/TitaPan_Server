package com.ncwu.titapan.constant;

import javax.print.attribute.Attribute;

/**
 * TODO 常量
 *
 * @author ddwl.
 * @date 2023/1/6 9:29
 */
public class Constant {
    // 约定：路径后都需要以 '\'  结尾

    // 系统文件存储根路径
    public static final String sys_storage_path = System.getProperty("user.dir").replace('\\','/') + "/src/main/resources/sysFile/";

    // 压缩文件临时路径
    public static final String zip_storage_path = System.getProperty("user.dir").replace('\\','/') + "/src/main/resources/zipTemp/";
    // 用户默认虚拟根路径
    public static final String user_root_path = "/";

    // 文件块临时存储根路径
    public static final String chunk_file_temp_path = System.getProperty("user.dir").replace('\\','/') + "/src/main/resources/sysFile/temp/";

    // 文件分块大小 15mb
    public static final long std_chunk_size = 1024*1024*15;

    // Attribute中的用户实体 键
    public static final String user = "user";

    // Attribute中的用户当前路径 键
    public static final String userPath = "userPath";

    // token秘钥
    public static final String KEY = "202117325";

    // token 过期时间 7天
    public static final int expireTime = 7;

    public static final String cookieName = "cookie";
    // cookie 有效期 一小时
    public static final int cookieMaxAge = 60*60;

    // 设置放行路径
    public static final String []excludePath = {
            "/**/*.js",                  //js静态资源
            "/**/*.css",                 //css静态资源
            "/**/*.jpg",                 //jpg图片资源
            "/**/*.png",                 //png图片资源
            "/login",                    //登录请求
            "/toLogin",                  //登录页面
            "/toRegistry",               //注册页面
            "/registry",                 //注册路径
    };


}
