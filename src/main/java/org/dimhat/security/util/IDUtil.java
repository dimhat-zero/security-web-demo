package org.dimhat.security.util;

import java.util.ArrayList;
import java.util.List;

/**
 * id转换工具类
 */
public class IDUtil {

    private static final String REGEX = ",";

    //id字符串转化成list
    public static List<Long> parseIds(String ids){
        return parseIds(ids,REGEX);
    }

    public static List<Long> parseIds(String ids,String regex){
        List<Long> result = new ArrayList<>();
        String[] idStrs = ids.split(regex);
        for(String idStr : idStrs) {
            Long id = Long.parseLong(idStr);
            result.add(id);
        }
        return result;
    }

    //list转换成id字符串
    public static <T> String toIdString(List<T> idList){
        return toIdString(idList,REGEX);
    }

    public static <T> String toIdString(List<T> idList,String regex){
        StringBuilder sb = new StringBuilder();
        for(T id : idList){
            sb.append(id).append(regex);
        }
        sb.setLength(sb.length()-1);
        return sb.toString();
    }
}
