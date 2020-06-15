package com.sy.bishe.ygou.service;

import com.sy.bishe.ygou.bean.PicturesBean;

import java.util.List;

public interface PictureService {
    public PicturesBean getPicturesById(int id);

    public List<PicturesBean> getPicturesList();

    public int add(PicturesBean picturesBean);

    public int delete(Integer id);

    List<PicturesBean> getPicturesByGoodsId(Integer id);
}
