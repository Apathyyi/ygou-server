package com.sy.bishe.ygou.bean;

public class BrandBean {
    private int brand_id ;

    private int brand_sortId;

    private String brand_name;

    private String brand_img;

    public BrandBean(int brand_id, int brand_sortId, String brand_name, String brand_img) {
        this.brand_id = brand_id;
        this.brand_sortId = brand_sortId;
        this.brand_name = brand_name;
        this.brand_img = brand_img;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public int getBrand_sortId() {
        return brand_sortId;
    }

    public void setBrand_sortId(int brand_sortId) {
        this.brand_sortId = brand_sortId;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getBrand_img() {
        return brand_img;
    }

    public void setBrand_img(String brand_img) {
        this.brand_img = brand_img;
    }
}
