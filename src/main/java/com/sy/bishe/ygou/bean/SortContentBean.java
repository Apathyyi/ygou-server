package com.sy.bishe.ygou.bean;

import java.util.List;

public class SortContentBean  {
    private int content_id;

    private String content_section ;

    private int content_verticalId;

    private List<BrandBean>brandBean = null;

    public SortContentBean(int content_id, String content_section, int content_verticalId) {
        this.content_id = content_id;
        this.content_section = content_section;
        this.content_verticalId = content_verticalId;
    }

    public int getContent_verticalId() {
        return content_verticalId;
    }

    public void setContent_verticalId(int content_verticalId) {
        this.content_verticalId = content_verticalId;
    }

    public int getContent_id() {
        return content_id;
    }

    public void setContent_id(int content_id) {
        this.content_id = content_id;
    }

    public String getContent_section() {
        return content_section;
    }

    public void setContent_section(String content_section) {
        this.content_section = content_section;
    }

    public List<BrandBean> getBrandBean() {
        return brandBean;
    }

    public void setBrandBean(List<BrandBean> brandBean) {
        this.brandBean = brandBean;
    }
}
