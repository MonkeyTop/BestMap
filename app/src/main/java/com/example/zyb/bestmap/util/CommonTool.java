package com.example.zyb.bestmap.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * get请求（获取指定地址的数据）
 * <p>
 * Created by zyb on 2016/9/13.
 */
public class CommonTool {
    public static String getRequest(String urlString, String codingType) {
        BufferedInputStream bis = null;
        ByteArrayOutputStream bos = null;
        InputStream is = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 决定返回值为JSON格式，不可缺少
            conn.setRequestProperty("Accept", "*/*");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                is = conn.getInputStream();

                bis = new BufferedInputStream(is);
                bos = new ByteArrayOutputStream();

                int length = 0;
                byte[] by = new byte[1024];
                while ((length = bis.read(by)) != -1) {
                    bos.write(by, 0, length);
                }
                bos.flush();
                String result = new String(bos.toByteArray(), codingType);
                return result;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("关闭失败！");
            }
        }
        return null;
    }

    /**
     * 下载图片网络
     */
    public static InputStream getImgInputStream(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");//设置请求方法为GET
            connection.setReadTimeout(10 * 1000);//设置请求过时时间为10秒
            connection.connect();
            if (connection.getResponseCode() == 200) {
                return connection.getInputStream();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
