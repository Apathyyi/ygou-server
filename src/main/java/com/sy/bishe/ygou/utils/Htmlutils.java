package com.sy.bishe.ygou.utils;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Htmlutils {
    //根据url从网络获取网页文本
    public static Document getHtmlTextByUrl(String url) {
        Document doc = null;
        try {
            //doc = Jsoup.connect(url).timeout(5000000).get();
            int i = (int)(Math.random() * 1000); //做一个随机延时，防止网站屏蔽
            while (i != 0) {
                i--;
            }
            doc = Jsoup.connect(url).data("query", "Java").userAgent("Mozilla").cookie("auth", "token").timeout(300000).post();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                doc = Jsoup.connect(url).timeout(5000000).get();
            } catch (IOException e1) { // TODO Auto-generated catch block
                e1.printStackTrace();
            } }
        return doc;
    }

    //根据元素属性获取某个元素内的elements列表
    public static Elements getEleByClass(Document doc, String className) {
        //<tr class="provincetr">
        Elements elements = null;
        elements = doc.select(className); //     tr.provincetr
        return elements; //此处返回的就是所有的tr集合
    }
    public static void ElemOneByURL(String url,String selecType){
        //得到网站内容
        Document htmlTextByPath =   Htmlutils.getHtmlTextByUrl(url);
        Elements ebc =  Htmlutils.getEleByClass(htmlTextByPath, selecType);
        for (Element e : ebc) {
            if (e != null) {
                for (Element ec: e.children()){ //一个tr的子元素td，td内包含a标签
                    if (ec.children().first() != null) {
                        String ownText = ec.children().first().ownText(); //a标签文本
                        System.out.println(ownText+"--------------------------------------------------------1级");
                        System.out.println(ec.children().first().attr("abs:href")); //得到的对应的href地址
                    }
                }
            }
        }
    }

}
