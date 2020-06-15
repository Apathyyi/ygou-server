package com.sy.bishe.ygou.bean;

public class PicturesBean {
    private int pictures_id;

    private String pictures_url;

    private int pictures_belong;

    private String pictures_tag;

    public String getPictures_tag() {
        return pictures_tag;
    }

    public void setPictures_tag(String pictures_tag) {
        this.pictures_tag = pictures_tag;
    }

    public int getPictures_id() {
        return pictures_id;
    }

    public void setPictures_id(int pictures_id) {
        this.pictures_id = pictures_id;
    }

    public String getPictures_url() {
        return pictures_url;
    }

    public void setPictures_url(String pictures_url) {
        this.pictures_url = pictures_url;
    }

    public int getPictures_belong() {
        return pictures_belong;
    }

    public void setPictures_belong(int pictures_belong) {
        this.pictures_belong = pictures_belong;
    }

    public PicturesBean(int pictures_id, String pictures_url, int pictures_belong, String pictures_tag) {
        this.pictures_id = pictures_id;
        this.pictures_url = pictures_url;
        this.pictures_belong = pictures_belong;
        this.pictures_tag = pictures_tag;
    }

    @Override
    public String toString() {
        return "PicturesBean{" +
                "pictures_id=" + pictures_id +
                ", pictures_url=" + pictures_url +
                ", pictures_belong=" + pictures_belong +
                '}';
    }
}
