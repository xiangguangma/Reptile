package com.baidu.prectice;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dllo on 17/11/23.
 */
public class Mp3Test {

    @Test
    public void test() throws IOException {

        Document doc = Jsoup.connect("http://music.baidu.com/")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36")
                .get();

        Elements lists = doc.select(".song");
        Elements songs = lists.select("a");

        for (int i = 0; i < songs.size(); i++) {
            Element song = songs.get(i);
            String attr = song.attr("abs:href");
            String attr1 = song.attr("abs:title");
            System.out.println(attr + "   " + attr1);

            Document document = Jsoup.connect(attr)
                    // 模拟火狐浏览器
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36")
                    .get();

            Elements select = document.select(".lyric-content");

            System.out.println(select.text());


        }

    }

    @Test
    public void test2() throws IOException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);


        Document document = Jsoup.connect("http://www.1ppt.com/xiazai/")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36")
                .get();

        Elements lists = document.select(".tplist");

        Elements urlList = lists.select("a");

        for (int i = 0; i < urlList.size(); i++) {

            Element element = urlList.get(i);
            String attr = element.attr("abs:href");

            Document doc = Jsoup.connect(attr)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36")
                    .get();

            Elements download = doc.select(".downurllist");

            String url = download.select("a").attr("abs:href");
            System.out.println(url);

//            String saveUrl = "/Users/dllo/Desktop/ppt/";
//
//            com.baidu.prectice.ZipDownloadUtils zdu = new com.baidu.prectice.ZipDownloadUtils(url, String.valueOf(i), saveUrl);
//
//            executorService.submit(zdu);


        }


    }




}
