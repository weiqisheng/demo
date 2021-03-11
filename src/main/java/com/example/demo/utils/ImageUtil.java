package com.example.demo.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Objects;

/**
 * @author weiqisheng
 * @Title: ImageUtil
 * @ProjectName utils
 * @Description: TODO
 * @date 2020/12/2516:33
 */
public class ImageUtil {

    private static final String filePath = "G:\\image";

    /**
     * 从浏览器下载url的图片
     * @param imageUrl
     * @param imageName
     * @param response
     */
    public static void imageDownload(String imageUrl, String imageName, HttpServletResponse response){
       if (StringUtils.isEmpty(imageUrl)){
           throw new RuntimeException("图片url不能为空！！");
       }
        try {
            URL url = new URL(imageUrl);
            BufferedInputStream in = null;
            in = new BufferedInputStream(url.openStream());
            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename=\"" + URLEncoder.encode(imageName + ".jpeg", "UTF-8") + "\"");
            int i;
            while ((i = in.read()) != -1) {
                response.getOutputStream().write(i);
            }
            in.close();
            response.getOutputStream().close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 本地图片在前端下载
     * @param name
     * @param response
     */
    public static void locationPath(String name,HttpServletResponse response){
        String path = "G:\\guge\\untitled.png";
        File file = new File(path);
        if (Objects.isNull(file)){
            throw new RuntimeException("没有该文件");
        }
        response.reset();
        response.setContentType("application/octet-stream");
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            response.setHeader("Content-Disposition","attachment;filename=\"" + URLEncoder.encode(name +".jpeg","UTF-8") + "\"");
            int i;
            while ((i = in.read()) != -1){
                response.getOutputStream().write(i);
            }
            in.close();
            response.getOutputStream().close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * url地址的图片下载到本地
     * @param imageUrl
     */
    public static void downloadImage(String imageUrl,String name){
        try {
            //构造url
            URL url = new URL(imageUrl);
            //打开链接
            URLConnection urlConnection = url.openConnection();
            //输入流
            InputStream inputStream = urlConnection.getInputStream();
            //1k的数据缓冲
            byte[] be =new byte[1024];
            //读取到的数据长度
            int len;
            //输出的文件流
            String fileName = "G:\\guge\\" + name +".jpg";
            File file = new File(fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            while ((len = inputStream.read(be)) != -1){
                fileOutputStream.write(be,0,len);
            }
            fileOutputStream.close();
            inputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
