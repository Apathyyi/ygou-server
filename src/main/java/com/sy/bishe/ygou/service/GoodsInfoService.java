package com.sy.bishe.ygou.service;

import com.sy.bishe.ygou.bean.GoodsInfoBean;

import java.util.List;

public interface GoodsInfoService {
    public GoodsInfoBean getGoodsInfoById(Integer id);

    public List<GoodsInfoBean> getGoodsInfoByType(Integer type);

    List<GoodsInfoBean> getGoodsInfoList();

    List<GoodsInfoBean> getListByKey(String key);

    List<GoodsInfoBean> getGoodsInfoByTypeOrderByAreaByHot(Integer type,String area);

    List<GoodsInfoBean> getGoodsInfoByTypeOrderByDistance(Integer type,String area);

    List<GoodsInfoBean> getGoodsInfoByTypeOrderByArea(Integer type,String area);

    List<GoodsInfoBean> getGoodsInfoByTypeOrderByDefault(Integer type, String area);

    List<GoodsInfoBean> getGoodsInfoByTypeOrderPriceDesc(Integer type, String area);

    List<GoodsInfoBean> getGoodsInfoByTypeOrderPriceAsc(Integer type, String area);

    List<GoodsInfoBean> getGoodsInfoByTypeOrderTimeDesc(Integer type, String area);

    List<GoodsInfoBean> getGoodsInfoByTypeOrderTimeAsc(Integer type, String area);

    int add(GoodsInfoBean goodsInfoBean);

    List<GoodsInfoBean> getGoodsInfoListByIds(List<Integer> ids);

    List<GoodsInfoBean> loadMore(Integer start,Integer end);

    List<GoodsInfoBean> getGoodsInfoListByPrice();

    List<GoodsInfoBean> getGoodsInfoOrderByHot();


    List<Integer> getGoodsInfoTypeById(List<Integer> goodsIds);

    List<GoodsInfoBean> getGoodsInfoListByName(String name);

    int deleteGoodsById(Integer id);

    List<GoodsInfoBean> getGoodsInfoListByBag();

    List<GoodsInfoBean> getGoodsInfoListByLatest();

    List<GoodsInfoBean> getGoodsInfoListByWoman();

    List<GoodsInfoBean> getGoodsInfoListByPhone(String phone);

    List<GoodsInfoBean> getGoodsInfoListByMusic();

    List<GoodsInfoBean> getGoodsInfoListByAdmin();

    int  updateGoodsToRefuse(String id);

    int updateGoodsToAgree(String id);

    List<GoodsInfoBean> getGoodsInfoListByNameAll(String name);

    List<GoodsInfoBean> getGoodsInfoBybanners();

    List<GoodsInfoBean> getGoodsInfoListByTool();

    List<GoodsInfoBean> getGoodsInfoListByBrand();

    List<GoodsInfoBean> getGoodsInfoListByBook();

    int updateGoodsHot(Integer id,String hot);
}
