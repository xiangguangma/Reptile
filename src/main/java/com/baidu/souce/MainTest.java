package com.baidu.souce;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by dllo on 17/11/23.
 */
public class MainTest {

    @Test
    public void test1(){

        // 爬虫测试

        String html = "<html><head><title>首页</title></head>" +
                "<body><p>这是一个页面</p></body></html>";

        // 解析html, Jsoup.parsr() 返回一个document
        Document doc = Jsoup.parse(html);

        System.out.println(doc.title());


    }


    @Test
    public void test2(){

        // 不完整的html, Jsoup.parseBodyFragment()

        String html = "<div><p>222</p>";

        Document doc = Jsoup.parseBodyFragment(html);

        System.out.println(doc);
    }

    @Test
    public void test() throws IOException {

        // 根据url进行获取document
        // http://www.runoob.com/eclipse/eclipse-install.html

        //伪造浏览器 userAgent , 用于爬数据
        Document doc = Jsoup.connect("http://www.runoob.com/eclipse/eclipse-install.html")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36")
                .get();

        System.out.println(doc);
        System.out.println(doc.title());

    }

    @Test
    public void test4() throws IOException {

        // 访问本地文件

        File file = new File("./src/main/webapp/WEB-INF/home1.html");

        Document doc = Jsoup.parse(file, "utf-8");

//        System.out.println(doc);

        // Jsoup里面, 关于标签的操作, 和js/jq非常类似
        Element element = doc.getElementById("div1");
//        System.out.println(element);

        // 获取标签的值
        // 获取标签内的文本信息
        System.out.println(element.text());
        System.out.println(element.html());

        // 获取标签的属性值
        System.out.println(element.attr("id"));
        System.out.println(element.tagName());

        // 获取一组标签, 使用Elements
        Elements elements = doc.getElementsByClass("divclass");

        System.out.println(elements);

        // 获取elements里的所有的值
        System.out.println(elements.text());


    }

    @Test
    public void test5() throws IOException {

        File file = new File("./src/main/webapp/WEB-INF/home.html");
        Document doc = Jsoup.parse(file, "utf-8");

        // select 后面跟(选择器)
        Elements elements = doc.select("#div1");

        System.out.println(elements);

        // 获取有效的标签
        Elements links = doc.select("a[href]");
        System.out.println(links);

        // 地址
        System.out.println(links.attr("abs:href"));

    }

    @Test
    public void allLinks() throws IOException {

        Document doc = Jsoup.connect("https://www.runoob.com/eclipse/eclipse-install.html")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36")
                .get();

        // 1. a标签
        // 2. 媒体文件
        // 3. 引用文件
        Elements nav = doc.select(".nav");

        Elements links = nav.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

        for (Element link : links) {
            System.out.println(link.attr("abs:href")+ "    "+ link.text());
        }

    }
}
