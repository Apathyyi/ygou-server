package com.sy.bishe.ygou.service;

import com.sy.bishe.ygou.bean.BrandBean;

import java.util.List;

public interface BrandService {
    public List<BrandBean> getBrandById(Integer id);

    String getBrandName(int type);

    List<Integer> getIdByName(String name);

    int getBrandIdByNameAndId(Integer type, String name);
}
