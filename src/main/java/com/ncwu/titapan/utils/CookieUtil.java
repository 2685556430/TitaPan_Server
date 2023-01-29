package com.ncwu.titapan.utils;

import com.ncwu.titapan.constant.Constant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO 类描述
 *
 * @author ddwl.
 * @date 2023/1/14 23:09
 */
public class CookieUtil {
    // 向response中添加cookie信息
    public static void setCookie(String value, HttpServletResponse response){
        // 加密后存储
        value = cookieEncode(value);

        Cookie cookie = new Cookie("cookie", value);
        cookie.setMaxAge(Constant.cookieMaxAge);
        // 注意 cookie domain(域) 与path(路径)作用 否则会导致切换账号异常！！！！！！
        cookie.setPath("/");
        //cookie.setDomain("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        response.addCookie(cookie);

    }

    // 从request中获得cookie信息
    public static Cookie getCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (Constant.cookieName.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }

    // 将request中的cookie信息清除
    public static void clearCookie(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (Constant.cookieName.equals(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
    }

    // 从cookie中获得信息
    public static String getInfo(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (Constant.cookieName.equals(cookie.getName())) {
                    return cookieDecode(cookie.getValue());
                }
            }
        }
        return null;
    }

    // cookie加密
    private static String cookieEncode(String text){
        String lock = String.valueOf(System.currentTimeMillis());

        String first = DESUtil.encode(Constant.KEY, text);
        // System.out.println("des加密结果：" + first);
        return merge(first, lock);
    }

    // cookie解密
    private static String cookieDecode(String text){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < text.length(); i++){
            if(i <= 13 * 2 - 1) {
                if(i % 2 != 0) continue;
            }
            res.append(text.charAt(i));
        }
        // System.out.println("反合并后结果：" + res);
        return DESUtil.decode(Constant.KEY, res.toString());
    }


    // 穿插两个字符串
    private static String merge(String a, String b){
        int i = 0, j = 0;
        int flag = 0;
        StringBuilder res = new StringBuilder();
        while(i < a.length() || j < b.length()){
            if(flag == 0){
                if(i < a.length()){
                    res.append(a.charAt(i));
                    i++;
                }
                flag = 1;
            }
            else{
                if(j < b.length()){
                    res.append(b.charAt(j));
                    j++;
                }
                flag=0;
            }
        }
        return res.toString();
    }
}
