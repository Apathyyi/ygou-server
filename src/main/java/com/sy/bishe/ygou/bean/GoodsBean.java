package com.sy.bishe.ygou.bean;

import java.util.List;

public class GoodsBean {
    private int goods_id;

    private String goods_text;

    private String goods_img;

    private String goods_spanSize ;

    public GoodsBean() {
    }
    private List<PicturesBean> banners = null;

    public List<PicturesBean> getBanners() {
        return banners;
    }
    public void setBanners(List<PicturesBean> banners) {
        this.banners = banners;
    }

    public String getGoods_spanSize() {
        return goods_spanSize;
    }

    public void setGoods_spanSize(String goods_spanSize) {
        this.goods_spanSize = goods_spanSize;
    }


    public GoodsBean(int goods_id, String goods_text, String goods_img, String goods_spanSize) {
        this.goods_id = goods_id;
        this.goods_text = goods_text;
        this.goods_img = goods_img;
        this.goods_spanSize = goods_spanSize;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_text() {
        return goods_text;
    }

    public void setGoods_text(String goods_text) {
        this.goods_text = goods_text;
    }

    public String getGoods_img() {
        return goods_img;
    }

    public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
