package com.zyy.generate.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtils {

    /**
     * 将文本写入文件
     */
    public static void saveTextFile(String value, String path) {
        try {
            File file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            @Cleanup
            FileWriter writer = new FileWriter(file);
            writer.write(value);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文本路径内容
     */
    public static String getText(String path) {
        File file = new File(path);

        try {
            return getText(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 通过流获取文本内容
     */
    public static String getText(InputStream inputStream) {
        try {
            @Cleanup
            InputStreamReader isr = new InputStreamReader(inputStream, "utf-8");
            @Cleanup
            BufferedReader bufferedReader = new BufferedReader(isr);

            StringBuilder builder = new StringBuilder();
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                builder.append(string).append("\n");
            }

            return builder.toString();
        } catch (UnsupportedEncodingException e) {
            log.error("未知的编码格式");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
