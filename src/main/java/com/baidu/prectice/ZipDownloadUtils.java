package com.baidu.prectice;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by dllo on 17/11/23.
 */
public class ZipDownloadUtils implements Runnable {

    private String zipUrl;
    private String zipName;
    private String saveFile;

    @Override
    public void run() {
        saveZip(zipUrl, zipName, saveFile);
    }

    public ZipDownloadUtils(String zipUrl, String zipName) {
        this.zipUrl = zipUrl;
        this.zipName = zipName;
    }

    public ZipDownloadUtils(String zipUrl, String zipName, String saveFile) {
        this.zipUrl = zipUrl;
        this.zipName = zipName;
        this.saveFile = saveFile;
    }

    public ZipDownloadUtils() {
    }

    private void saveZip(String zipUrl, String zipName, String saveFile){

        URL url = null;

        try {
            url = new URL(zipUrl);

            URLConnection connection = url.openConnection();

            InputStream is = connection.getInputStream();

            File file = new File(saveFile);
            if (!file.exists()){
                file.mkdirs();
            }

            OutputStream os = new FileOutputStream(new File(saveFile, zipName + ".zip"));

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
