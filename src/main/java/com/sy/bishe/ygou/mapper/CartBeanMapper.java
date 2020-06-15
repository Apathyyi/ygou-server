package com.sy.bishe.ygou.mapper;

import com.sy.bishe.ygou.bean.CartBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartBeanMapper {
    @Select("SELECT * FROM tb_cart WHERE cart_user_id=#{id}")
    public List<CartBean> getGoodsIdByUserId(Integer id);

    @Update("UPDATE tb_cart SET cart_goods_count = cart_goods_count+1 WHERE cart_goods_id = #{id}")
    public int  addCartByGoodsId(Integer id);

    @Update("UPDATE tb_cart SET cart_goods_count = cart_goods_count-1 WHERE cart_goods_id = #{id}")
    public int subCartByGoodsId(Integer id);

    @Delete("DELETE FROM tb_cart WHERE cart_goods_id = #{id}")
    public int deleteCartGoods(Integer id);

    @Insert("INSERT INTO tb_cart(cart_goods_id,cart_user_id,cart_goods_count,cart_name,cart_desc,cart_price,cart_thumb) VALUES(#{cartBean.cart_goods_id},#{cartBean.cart_user_id},#{cartBean.cart_goods_count},#{cartBean.cart_name},#{cartBean.cart_desc},#{cartBean.cart_price},#{cartBean.cart_thumb})")
    public int addGoodsToCart(@Param(value = "cartBean") CartBean cartBean);

    @Select("SELECT * FROM tb_cart WHERE cart_goods_id = #{id}")
    public CartBean getGoodsByGoodsId(Integer id);

    @Delete("DELETE FROM tb_cart WHERE cart_user_id = #{id}")
    public int deleteCartByUserId(Integer id);
}
