package com.sy.bishe.ygou.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class UploadUtils {
    /**
     * 生成随机文件名
     * @param filename
     * @return
     */
    public static String getrandomFilename(String filename){
        int sub=filename.lastIndexOf(".");
        if(sub==-1){
            return UUID.randomUUID().toString().replace("-", "")+".png";
        }else {
            String extions = filename.substring(sub);
            return UUID.randomUUID().toString().replace("-", "") + extions;
        }
    }
    /**
     * 生成二级文件目录
     * @param uuidFilename
     * @return
     */
    public static String getpath(String uuidFilename){
        int code1 = uuidFilename.hashCode();
        int dir1 = code1 & 0xf;//一级目录
        int code2= code1>>>4;
        int dir2=code2 & 0xf;//二级目录
        return "/"+dir1+"/"+dir2;
    }

        public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
            File targetFile = new File(filePath);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(filePath+fileName);
            out.write(file);
            out.flush();
            out.close();

    }
}
