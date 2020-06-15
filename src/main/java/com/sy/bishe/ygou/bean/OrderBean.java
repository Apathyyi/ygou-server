package com.sy.bishe.ygou.bean;

public class OrderBean  {
    private int order_id;

    private String order_goods_releaseimg;

    private String order_goods_releasename;

    private String order_title;

    private int order_user_id;

    private int order_count;

    private Double order_price;

    private String order_time;

    private String order_thumb;

    private String order_tag;

    private int order_goods_id;

    private String order_goods_lable;

    private String order_trans_cast;

    private String order_trans_count;

    private int order_total_count;

    private Double order_total_price;

    public OrderBean() {
    }

    public OrderBean(int order_id, String order_goods_releaseimg, String order_goods_releasename, String order_title, int order_user_id, int order_count, Double order_price, String order_time, String order_thumb, String order_tag, int order_goods_id, String order_goods_lable, String order_trans_cast, String order_trans_count, int order_total_count, Double order_total_price) {
        this.order_id = order_id;
        this.order_goods_releaseimg = order_goods_releaseimg;
        this.order_goods_releasename = order_goods_releasename;
        this.order_title = order_title;
        this.order_user_id = order_user_id;
        this.order_count = order_count;
        this.order_price = order_price;
        this.order_time = order_time;
        this.order_thumb = order_thumb;
        this.order_tag = order_tag;
        this.order_goods_id = order_goods_id;
        this.order_goods_lable = order_goods_lable;
        this.order_trans_cast = order_trans_cast;
        this.order_trans_count = order_trans_count;
        this.order_total_count = order_total_count;
        this.order_total_price = order_total_price;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_goods_releaseimg() {
        return order_goods_releaseimg;
    }

    public void setOrder_goods_releaseimg(String order_goods_releaseimg) {
        this.order_goods_releaseimg = order_goods_releaseimg;
    }

    public String getOrder_goods_releasename() {
        return order_goods_releasename;
    }

    public void setOrder_goods_releasename(String order_goods_releasename) {
        this.order_goods_releasename = order_goods_releasename;
    }

    public String getOrder_title() {
        return order_title;
    }

    public void setOrder_title(String order_title) {
        this.order_title = order_title;
    }

    public int getOrder_user_id() {
        return order_user_id;
    }

    public void setOrder_user_id(int order_user_id) {
        this.order_user_id = order_user_id;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public Double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(Double order_price) {
        this.order_price = order_price;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getOrder_thumb() {
        return order_thumb;
    }

    public void setOrder_thumb(String order_thumb) {
        this.order_thumb = order_thumb;
    }

    public String getOrder_tag() {
        return order_tag;
    }

    public void setOrder_tag(String order_tag) {
        this.order_tag = order_tag;
    }

    public int getOrder_goods_id() {
        return order_goods_id;
    }

    public void setOrder_goods_id(int order_goods_id) {
        this.order_goods_id = order_goods_id;
    }

    public String getOrder_goods_lable() {
        return order_goods_lable;
    }

    public void setOrder_goods_lable(String order_goods_lable) {
        this.order_goods_lable = order_goods_lable;
    }

    public String getOrder_trans_cast() {
        return order_trans_cast;
    }

    public void setOrder_trans_cast(String order_trans_cast) {
        this.order_trans_cast = order_trans_cast;
    }

    public String getOrder_trans_count() {
        return order_trans_count;
    }

    public void setOrder_trans_count(String order_trans_count) {
        this.order_trans_count = order_trans_count;
    }

    public int getOrder_total_count() {
        return order_total_count;
    }

    public void setOrder_total_count(int order_total_count) {
        this.order_total_count = order_total_count;
    }

    public Double getOrder_total_price() {
        return order_total_price;
    }

    public void setOrder_total_price(Double order_total_price) {
        this.order_total_price = order_total_price;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "order_id=" + order_id +
                ", order_goods_releaseimg='" + order_goods_releaseimg + '\'' +
                ", order_goods_releasename='" + order_goods_releasename + '\'' +
                ", order_title='" + order_title + '\'' +
                ", order_user_id=" + order_user_id +
                ", order_count=" + order_count +
                ", order_price=" + order_price +
                ", order_time='" + order_time + '\'' +
                ", order_thumb='" + order_thumb + '\'' +
                ", order_tag='" + order_tag + '\'' +
                ", order_goods_id=" + order_goods_id +
                ", order_goods_lable='" + order_goods_lable + '\'' +
                ", order_trans_cast='" + order_trans_cast + '\'' +
                ", order_trans_count='" + order_trans_count + '\'' +
                ", order_total_count=" + order_total_count +
                ", order_total_price=" + order_total_price +
                '}';
    }
}
