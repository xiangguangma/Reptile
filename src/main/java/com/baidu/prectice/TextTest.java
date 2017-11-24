package com.baidu.prectice;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by dllo on 17/11/23.
 */
public class TextTest {

    @Test
    public void test() throws IOException {

        Document doc = Jsoup.connect("http://tv.cctv.com/2017/11/22/VIDEwWKeE1fJdb6hrsapkaVV171122.shtml")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36")
                .get();

        Elements elements = doc.select(".cnt_bd");

        Elements ps = elements.select("p");

        for (int i = 0; i < ps.size(); i++) {
            System.out.print(ps.get(i).text());
            System.out.println();
        }

    }

    @Test

    public void test1() throws IOException {

        Document doc = Jsoup.connect("http://www.chinanews.com/")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36")
                .get();

        Elements news = doc.select(".new_con_border_b");

        Elements elements = news.select("a");

        for (int i = 0; i <elements.size(); i++) {

            String newsUrl = elements.get(i).attr("abs:href");

            Document d = Jsoup.connect(newsUrl)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36")
                    .get();


            Elements select = d.select(".left_zw");

            String text = select.text();
            System.out.println(text);

            saveText(text, i);

        }

    }

    public void saveText(String text, int i){

        try {

            File file = new File("/Users/dllo/Desktop/ppt/"+ i +".txt");

            FileWriter writer = new FileWriter(file);

            writer.write(text + "\n");
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
