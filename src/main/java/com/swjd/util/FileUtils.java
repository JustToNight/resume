package com.swjd.util;
<<<<<<< HEAD
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
=======

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
>>>>>>> resume/master

public class FileUtils {

    /**
<<<<<<< HEAD
     *
     * @param file 文件
     * @param path   文件存放路径
     * @param fileName 原文件名
     * @return
     */
    public static String upload(MultipartFile file, String path, String fileName){

//        String newFileName = com.swjd.util.FileNameUtils.getFileName(fileName);
//
//        // 生成新的文件名
        String realPath = path + "/" + fileName;
=======
     * @param file     文件
     * @param path     文件存放路径
     * @param fileName 原文件名
     * @return
     */
    public static String upload(MultipartFile file, String path, String fileName) {

        String newFileName = FileNameUtils.getFileName(fileName);

        // 生成新的文件名
        String realPath = path + "/" + newFileName;
>>>>>>> resume/master

        File dest = new File(realPath);

        //判断文件父目录是否存在
<<<<<<< HEAD
        if(!dest.getParentFile().exists()){
=======
        if (!dest.getParentFile().exists()) {
>>>>>>> resume/master
            dest.getParentFile().mkdir();
        }

        try {
            //保存文件
            file.transferTo(dest);
<<<<<<< HEAD
            return realPath;
=======
            return newFileName;
>>>>>>> resume/master
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
<<<<<<< HEAD

=======
    }

    public static void download(File file, OutputStream outputStream) {
        if (file.exists()) {
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;// 文件输入流
            BufferedInputStream bis = null;// 带缓冲区的输入流
            OutputStream os = null;// 文件输出流
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                os = outputStream;
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bis != null) {
                        bis.close();
                    }
                    if (fis != null) {
                        fis.close();
                    }
                    if (os != null) {
                        os.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
>>>>>>> resume/master
    }
}
