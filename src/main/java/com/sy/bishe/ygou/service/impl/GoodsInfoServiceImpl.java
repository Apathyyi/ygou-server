package com.sy.bishe.ygou.service.impl;

import com.sy.bishe.ygou.bean.GoodsInfoBean;
import com.sy.bishe.ygou.mapper.GoodsInfoMapper;
import com.sy.bishe.ygou.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsInfoServiceImpl implements GoodsInfoService{

    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public GoodsInfoBean getGoodsInfoById(Integer id) {
        return goodsInfoMapper.getGoodsInfoById(id);
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoByType(Integer type) {
        return goodsInfoMapper.getGoodsInfoByType(type);
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoList() {
        return goodsInfoMapper.getGoodsInfoList();
    }

    @Override
    public List<GoodsInfoBean> getListByKey(String key) {
        return goodsInfoMapper.getListByKey(key);
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoByTypeOrderByAreaByHot(Integer type,String area) {
        return goodsInfoMapper.getGoodsInfoByTypeOrderByHot(type,area);
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoByTypeOrderByDistance(Integer type,String area) {
        return goodsInfoMapper.getGoodsInfoByTypeOrderByDistance(type,area);
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoByTypeOrderByArea(Integer type,String area) {
        return goodsInfoMapper.getGoodsInfoByTypeOrderByArea(type, area);
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoByTypeOrderByDefault(Integer type, String area) {
        return goodsInfoMapper.getGoodsInfoByTypeOrderByDefault(type,area);
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoByTypeOrderPriceDesc(Integer type, String area) {
        return goodsInfoMapper.getGoodsInfoByTypeOrderPriceDesc(type,area);
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoByTypeOrderPriceAsc(Integer type, String area) {
        return goodsInfoMapper.getGoodsInfoByTypeOrderPriceAsc(type,area);
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoByTypeOrderTimeDesc(Integer type, String area) {
        return goodsInfoMapper.getGoodsInfoByTypeOrderTimeDesc(type,area);
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoByTypeOrderTimeAsc(Integer type, String area) {
        return goodsInfoMapper.getGoodsInfoByTypeOrderTimeAsc(type,area);
    }

    @Override
    public int add(GoodsInfoBean goodsInfoBean) {
        return goodsInfoMapper.add(goodsInfoBean);
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoListByIds(List<Integer> ids) {
        return goodsInfoMapper.getGoodsInfoListByIds(ids);
    }

    @Override
    public List<GoodsInfoBean> loadMore(Integer count,Integer end) {
        return goodsInfoMapper.loadMore(count,end);
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoListByPrice() {
        return goodsInfoMapper.getGoodsInfoListByPrice();
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoOrderByHot() {
        return goodsInfoMapper.getGoodsInfoOrderByHot();
    }

    @Override
    public List<Integer> getGoodsInfoTypeById(List<Integer> goodsIds) {
        return goodsInfoMapper.getGoodsInfoTypeById(goodsIds);
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoListByName(String name) {
        return goodsInfoMapper.getGoodsInfoListByName(name);
    }

    @Override
    public int deleteGoodsById(Integer id) {
        return goodsInfoMapper.deleteGoodsById(id);
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoListByBag() {
        return goodsInfoMapper.getGoodsInfoListByBag();
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoListByLatest() {
        return goodsInfoMapper.getGoodsInfoListByLatest();
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoListByWoman() {
        return goodsInfoMapper.getGoodsInfoListByWoman();
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoListByPhone(String phone) {
        return goodsInfoMapper.getGoodsInfoListByPhone(phone);
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoListByMusic() {
        return goodsInfoMapper.getGoodsInfoListByMusic();
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoListByAdmin() {
        return goodsInfoMapper.getGoodsInfoListByAdmin();
    }

    @Override
    public int updateGoodsToRefuse(String id) {
        return goodsInfoMapper.updateGoodsToRefuse(id);
    }

    @Override
    public int updateGoodsToAgree(String id) {
        return goodsInfoMapper.updateGoodsToAgree(id);
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoListByNameAll(String name) {
        return goodsInfoMapper.getGoodsInfoListByNameAll(name);
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoBybanners() {
        return goodsInfoMapper.getGoodsInfoBybanners();
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoListByTool() {
        return goodsInfoMapper.getGoodsInfoListByTool();
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoListByBrand() {
        return goodsInfoMapper.getGoodsInfoListByBrand();
    }

    @Override
    public List<GoodsInfoBean> getGoodsInfoListByBook() {
        return goodsInfoMapper.getGoodsInfoListByBook();
    }

    @Override
    public int updateGoodsHot(Integer id,String hot) {
        return goodsInfoMapper.updateGoodsHot(id,hot);
    }
}
