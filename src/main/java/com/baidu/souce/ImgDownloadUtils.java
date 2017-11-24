package com.baidu.souce;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by dllo on 17/11/23.
 */
public class ImgDownloadUtils extends Thread {

    private String imgURL;
    private String imgName;

    public ImgDownloadUtils() {
    }

    // 利用内部属性赋值
    public ImgDownloadUtils(String imgURL, String imgName) {
        this.imgURL = imgURL;
        this.imgName = imgName;
    }

    @Override
    public void run() {

        saveImg(imgURL, imgName);

    }

    /**
     *  下载图片的方法
     */
    public void saveImg(String imgURL, String imgName){

        //具体下载代码
        // 创建URL
        URL url = null;
        try {
            url = new URL(imgURL);
            // 创建连接
            URLConnection connection = url.openConnection();

            // 获取数据流
            InputStream is = connection.getInputStream();

            // 定位图片位置
            File file = new File("/Users/dllo/Desktop/imgs/");

            if (!file.exists()){
                file.mkdirs();
            }

            OutputStream os = new FileOutputStream(new File("/Users/dllo/Desktop/imgs/", imgName + ".png"));

            // 写入图片到本地
            byte[] buf = new byte[1024];

            int length = 0;

            while ((length = is.read(buf)) != -1){
                os.write(buf, 0, length);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
