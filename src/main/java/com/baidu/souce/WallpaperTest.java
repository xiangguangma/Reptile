package com.baidu.souce;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dllo on 17/11/23.
 */
public class WallpaperTest {

    public static void main(String[] args) throws IOException {

        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Document doc = Jsoup.connect("https://bing.ioliu.cn/")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36")
                .get();

        // 每张图片对应的所有信息都包含在一个item中
        Elements items = doc.select(".item");

        // 获取图片的下载地址/下载a标签
        Elements downloadA = items.select("a.download");

        System.out.println(downloadA);

        for (int i = 0; i < downloadA.size(); i++) {

            Element element = downloadA.get(i);

            // 获取图片地址
            String imgURL = element.attr("abs:href");

            // 下载
//            System.out.println(imgURL);

            ImgDownloadUtils idu = new ImgDownloadUtils(imgURL, String.valueOf(i));

            executorService.submit(idu);
//            idu.start();
        }

    }
}
