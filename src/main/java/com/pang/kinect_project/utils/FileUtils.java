package com.pang.kinect_project.utils;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    /**
     * 检查文件夹是否存在
     * 存在，则直接返回
     * 不存在，则创建
     * @param path
     */
    public static void createFile(String path){
        File file = new File(path);
        if(file.exists()) {
            return;
        } else {
            //getParentFile() 获取上级目录（包含文件名时无法直接创建目录的）
            if (!file.getParentFile().exists()) {
                System.out.println("not exists");
                //创建上级目录
                file.getParentFile().mkdirs();
            }
            try {
                //在上级目录里创建文件
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 创建文件夹
     * @param path
     */
    public static void createDir(String path) {
        File folder = new File(path);
        if (!folder.exists() && !folder.isDirectory()) {
            folder.setWritable(true, false);
            folder.mkdirs();
            System.out.println("创建文件夹");
        } else {
            System.out.println("文件夹已存在");
        }
    }

}
