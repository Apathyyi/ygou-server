package com.sy.bishe.ygou.service;

import com.sy.bishe.ygou.bean.CartBean;

import java.util.List;

public interface CartService {
    public List<CartBean> getGoodsIdByUserId(Integer id);

    public int addCartByGoodsId(Integer id);

    public int subCartByGoodsId(Integer id);

    public int deleteCartGoods(Integer id);

    public int addGoodsToCart(CartBean cartBean);

    public CartBean getGoodsByGoodsId(Integer id);

    public int deleteCartByUserId(Integer id);
}
