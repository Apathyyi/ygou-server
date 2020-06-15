package com.sy.bishe.ygou.service.impl;

import com.sy.bishe.ygou.bean.CartBean;
import com.sy.bishe.ygou.mapper.CartBeanMapper;
import com.sy.bishe.ygou.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartBeanMapper cartBeanMapper;

    @Override
    public List<CartBean> getGoodsIdByUserId(Integer id) {
        return cartBeanMapper.getGoodsIdByUserId(id);
    }

    @Override
    public int addCartByGoodsId(Integer id) {
        return cartBeanMapper.addCartByGoodsId(id);
    }

    @Override
    public int subCartByGoodsId(Integer id) {
        return cartBeanMapper.subCartByGoodsId(id);
    }

    @Override
    public int deleteCartGoods(Integer id) {
        return cartBeanMapper.deleteCartGoods(id);
    }

    @Override
    public int addGoodsToCart(CartBean cartBean) {
        return cartBeanMapper.addGoodsToCart(cartBean);
    }

    @Override
    public CartBean getGoodsByGoodsId(Integer id) {
        return cartBeanMapper.getGoodsByGoodsId(id);
    }

    @Override
    public int deleteCartByUserId(Integer id) {
        return cartBeanMapper.deleteCartByUserId(id);
    }
}
