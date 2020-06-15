package com.sy.bishe.ygou.bean;

public class AdminBean {

    private int id;

    private String adm_name;

    private String adm_pass;

    public AdminBean() {
    }

    public AdminBean(int id, String adm_name, String adm_pass) {
        this.id = id;
        this.adm_name = adm_name;
        this.adm_pass = adm_pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdm_name() {
        return adm_name;
    }

    public void setAdm_name(String adm_name) {
        this.adm_name = adm_name;
    }

    public String getAdm_pass() {
        return adm_pass;
    }

    public void setAdm_pass(String adm_pass) {
        this.adm_pass = adm_pass;
    }
}
