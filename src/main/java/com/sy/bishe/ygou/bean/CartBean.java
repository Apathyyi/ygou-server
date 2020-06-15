package com.sy.bishe.ygou.bean;

public class CartBean {

    private int cart_id ;

    private int cart_goods_id ;

    private int cart_user_id;

    private int cart_goods_count;

    private String cart_name ;

    private String cart_desc ;

    private Double cart_price ;

    private String cart_thumb ;

    public CartBean() {
    }

    public CartBean(int cart_id, int cart_goods_id, int cart_user_id, int cart_goods_count, String cart_name, String cart_desc, Double cart_price, String cart_thumb) {
        this.cart_id = cart_id;
        this.cart_goods_id = cart_goods_id;
        this.cart_user_id = cart_user_id;
        this.cart_goods_count = cart_goods_count;
        this.cart_name = cart_name;
        this.cart_desc = cart_desc;
        this.cart_price = cart_price;
        this.cart_thumb = cart_thumb;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getCart_goods_id() {
        return cart_goods_id;
    }

    public void setCart_goods_id(int cart_goods_id) {
        this.cart_goods_id = cart_goods_id;
    }

    public int getCart_user_id() {
        return cart_user_id;
    }

    public void setCart_user_id(int cart_user_id) {
        this.cart_user_id = cart_user_id;
    }

    public int getCart_goods_count() {
        return cart_goods_count;
    }

    public void setCart_goods_count(int cart_goods_count) {
        this.cart_goods_count = cart_goods_count;
    }

    public String getCart_name() {
        return cart_name;
    }

    public void setCart_name(String cart_name) {
        this.cart_name = cart_name;
    }

    public String getCart_desc() {
        return cart_desc;
    }

    public void setCart_desc(String cart_desc) {
        this.cart_desc = cart_desc;
    }

    public Double getCart_price() {
        return cart_price;
    }

    public void setCart_price(Double cart_price) {
        this.cart_price = cart_price;
    }

    public String getCart_thumb() {
        return cart_thumb;
    }

    public void setCart_thumb(String cart_thumb) {
        this.cart_thumb = cart_thumb;
    }
}
