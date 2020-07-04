package com.cskaoyan.project1.utils;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HttptUtils {

    public static String getRequestBody(HttpServletRequest request) throws IOException {
        InputStream inputStream = request.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes,0,length);
        }
        //字符串转为 java 对象
        return outputStream.toString();

    }
}
