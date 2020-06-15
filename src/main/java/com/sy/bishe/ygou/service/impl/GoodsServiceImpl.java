package com.sy.bishe.ygou.service.impl;

import com.sy.bishe.ygou.bean.GoodsBean;
import com.sy.bishe.ygou.mapper.GoodsMapper;
import com.sy.bishe.ygou.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public GoodsBean getGoodsById(Integer id) {
        return goodsMapper.getGoodsById(id);
    }

    @Override
    public List<GoodsBean> getGoodsList() {
        return goodsMapper.getGoodsList();
    }

    @Override
    public int add(GoodsBean goodsBean) {
        return goodsMapper.add(goodsBean);
    }

    @Override
    public int delete(Integer id) {
        return goodsMapper.delete(id);
    }

    @Override
    public List<GoodsBean> getGoodsListByKey(String key) {
        return goodsMapper.getGoodsListByKey(key);
    }
}
