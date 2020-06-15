package com.sy.bishe.ygou.service;

import com.sy.bishe.ygou.bean.GoodsBean;

import java.util.List;

public interface GoodsService {
    public GoodsBean getGoodsById(Integer id);

    public List<GoodsBean> getGoodsList();

    public int add(GoodsBean goodsBean);

    public int delete(Integer id);

    List<GoodsBean> getGoodsListByKey(String key);
}
