package com.sy.bishe.ygou.mapper;

import com.sy.bishe.ygou.bean.GoodsBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {
    @Select("SELECT * FROM tb_goods WHERE goods_id = #{id}")
    GoodsBean getGoodsById(int id);



    @Select("SELECT * FROM tb_goods")
    List<GoodsBean> getGoodsList();

    //增
    @Insert("INSERT INTO tb_goods(goods_id, goods_text,goods_img,goods_spanSize) VALUES(#{goodsBean.goods_id},#{goodsBean.goods_text},#{goodsBean.goods_img},#{goodsBean.goods_spanSize})")
    public int add(@Param("goodsBean") GoodsBean goodsBean);

    //删
    @Delete("DELETE FROM tb_goods WHERE goods_id=#{id}")
    public int delete(Integer id);

    @Select("SELECT * FROM tb_goods WHERE goods_text like CONCAT('%',#{key},'%')")
    List<GoodsBean> getGoodsListByKey(String key);
}
