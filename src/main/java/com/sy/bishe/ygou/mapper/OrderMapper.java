package com.sy.bishe.ygou.mapper;


import com.sy.bishe.ygou.bean.OrderBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    @Insert("INSERT INTO tb_order(order_user_id,order_title,order_count,order_price,order_time,order_thumb,order_tag,order_goods_id," +
            "order_goods_releaseimg,order_goods_releasename,order_goods_lable,order_trans_cast,order_trans_count,order_total_count,order_total_price) " +
            "VALUES(#{orderBean.order_user_id},#{orderBean.order_title},#{orderBean.order_count},#{orderBean.order_price}," +
            "#{orderBean.order_time},#{orderBean.order_thumb},#{orderBean.order_tag},#{orderBean.order_goods_id},#{orderBean.order_goods_releaseimg}," +
            "#{orderBean.order_goods_releasename},#{orderBean.order_goods_lable},#{orderBean.order_trans_cast},#{orderBean.order_trans_count},#{orderBean.order_total_count},#{orderBean.order_total_price})")
    public int add(@Param("orderBean") OrderBean orderBean);

    @Select("SELECT *  FROM tb_order WHERE order_user_id = #{id} ")
    List<OrderBean> getOrderListByUserId(Integer id);


    @Select("SELECT *  FROM tb_order WHERE order_user_id = #{id} and order_tag = #{tag}")
    List<OrderBean> getOrderListByUserIdAndType(Integer id, String tag);


    @Select("SELECT *  FROM tb_order WHERE order_goods_releasename = #{name}")
    List<OrderBean> getOrderListByUserName(String name);

    @Delete("DELETE FROM tb_order WHERE order_id = #{id}")
    int deleteOrderById(Integer id);

    @Update("UPDATE tb_order SET order_tag ='待收货' WHERE order_goods_id = #{id}")
    int updateTagToSendById(Integer id);

    @Select("SELECT * FROM tb_order WHERE order_id = #{id}")
    OrderBean getOrderById(Integer id);

    @Update("UPDATE tb_order SET order_tag = '已评价' WHERE order_id = #{id}")
    int updateTagToEvlById(Integer id);

    @Update("UPDATE tb_order SET order_tag = '待评价' WHERE order_id = #{id}")
    int updateTagToReceiveById(Integer id);
}
