package com.dragon.media.common.utils;

import java.beans.Encoder;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @program admin
 * @description: 文件处理类
 * @author: Gaofei
 * @create: 2019/01/02 14:24
 */

public class FileUtil {

    //文件上传工具类服务方法

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception{

        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    /**
     * 删除文件
     * @param path
     */
    public static void delFile(String path){

        File file = new File(path);
        if(file.exists()&&file.isFile())
            file.delete();
    }

    /**
     * 音频文件获取文件时长
     * @param source
     * @return
     */
   /* public static Long getDuration(File source) {

        Encoder encoder = new Encoder();
        long ls = 0;
        MultimediaInfo m;
        try {
            m = encoder.getInfo(source);
            ls = m.getDuration()/1000;

        } catch (Exception e) {
            System.out.println("获取音频时长有误：" + e.getMessage());
        }
        return ls;
    }*/




}
