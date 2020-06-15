package com.sy.bishe.ygou;

import com.sy.bishe.ygou.mapper.UserMapper;
import com.sy.bishe.ygou.utils.Htmlutils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@MapperScan(basePackageClasses = UserMapper.class)
public class YgouApplication {
    private static List<String> city = new ArrayList<>();
    private static List<String> href = new ArrayList<>();
    private static List<String> provice = new ArrayList<>();
    public static void main(String[] args) {
                String url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2015/index.html";
                String selectType="tr.provincetr";
//                ElemOneByURL(url,selectType);
                SpringApplication.run(YgouApplication.class, args);
    }
    public static void ElemOneByURL(String url,String selecType){
        //得到网站内容
        Document htmlTextByPath = Htmlutils.getHtmlTextByUrl(url);
        Elements ebc =  Htmlutils.getEleByClass(htmlTextByPath, selecType);
        for (Element e : ebc) {
            if (e != null) {
                for (Element ec: e.children()){ //一个tr的子元素td，td内包含a标签
                    if (ec.children().first() != null) {
                        String ownText = ec.children().first().ownText(); //a标签文本
                        //打印二级
                        System.out.print(ownText);
                        city.add(ownText);
                        String attr = ec.children().first().attr("abs:href");
                        System.out.print(attr);
                    }
                }
            }
        }
    }
}
