package com.zyy.generate.util;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 
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
                
                sb.append(s.substring(0, 1).toUpperCase());
                if (s.length() > 1) {
                    sb.append(s.substring(1));
                }
            }

            return sb.toString();
    }
    
    /**
     * 返回首字母大写的字符串
     */
    public static String upperFirstChar(String string) {
            String name = str2hump(string);
            String firstChar = name.substring(0, 1);
            name = name.replaceFirst(firstChar, firstChar.toUpperCase());

            return name;
    }

    /**
     * 变量名
     * 
     * @param beanName
     * @return
     */
    public static String lowerFirstChar(String beanName) {
            String name = str2hump(beanName);
            String firstChar = name.substring(0, 1);
            name = name.replaceFirst(firstChar, firstChar.toLowerCase());

            return name;
    }
}
