package com.sy.bishe.ygou.bean;

public class AdvBean {

    private int adv_id;

    private String adv_title;

    private String adv_desc;

    private String adv_thumb;

    public AdvBean() {
    }

    public AdvBean(int adv_id, String adv_title, String adv_desc, String adv_thumb) {
        this.adv_id = adv_id;
        this.adv_title = adv_title;
        this.adv_desc = adv_desc;
        this.adv_thumb = adv_thumb;
    }

    public int getAdv_id() {
        return adv_id;
    }

    public void setAdv_id(int adv_id) {
        this.adv_id = adv_id;
    }

    public String getAdv_title() {
        return adv_title;
    }

    public void setAdv_title(String adv_title) {
        this.adv_title = adv_title;
    }

    public String getAdv_desc() {
        return adv_desc;
    }

    public void setAdv_desc(String adv_desc) {
        this.adv_desc = adv_desc;
    }

    public String getAdv_thumb() {
        return adv_thumb;
    }

    public void setAdv_thumb(String adv_thumb) {
        this.adv_thumb = adv_thumb;
    }
}
