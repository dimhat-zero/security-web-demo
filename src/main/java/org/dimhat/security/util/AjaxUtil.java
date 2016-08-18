package org.dimhat.security.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by think on 2016/8/18.
 */
public class AjaxUtil {

    //判断是否是ajax请求
    public  static boolean isAjaxRequest(HttpServletRequest req){
        if (req.getHeader("x-requested-with") != null && req.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
            return true;
        }
        return false;
    }
}
