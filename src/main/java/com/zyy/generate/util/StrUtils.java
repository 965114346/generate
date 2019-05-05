package com.zyy.generate.util;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yangyang.zhang
 * 字符串转化工具类
 */
public class StrUtils {

    /**
     * 字符串转为驼峰
     */
    public static String str2hump(String str) {
            StringBuilder sb = new StringBuilder();
            
            if (StringUtils.isEmpty(str)) {
                return sb.toString();
            }
            
            if (!str.contains("_")) {
                sb.append(str);
                return sb.toString();
            }

            List<String> chars = Arrays.asList(str.split("_"));
            if (chars.isEmpty()) {
                return sb.toString();
            }
            
            sb.append(chars.get(0));
            for (int i = 1; i < chars.size(); i++) {
                String s = chars.get(i);
                if (StringUtils.isEmpty(s)) {
                    continue;
                }

                sb.append(StringUtils.capitalize(s));
            }

            return sb.toString();
    }

    public static void main(String[] args) {
        String t_blog = str2hump("t_blog");
        System.out.println(t_blog);
    }
}
