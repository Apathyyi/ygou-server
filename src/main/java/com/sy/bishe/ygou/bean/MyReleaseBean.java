package com.sy.bishe.ygou.bean;


//发布
public class MyReleaseBean {
    private int id;

    private String img;

    private String title;

    private Double price;

    private int count;

    private String time;

    private String tag ;

    private String user_name;

    public MyReleaseBean() {
    }

    public MyReleaseBean(int id, String img, String title, Double price, int count, String time, String tag, String user_name) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.price = price;
        this.count = count;
        this.time = time;
        this.tag = tag;
        this.user_name = user_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
