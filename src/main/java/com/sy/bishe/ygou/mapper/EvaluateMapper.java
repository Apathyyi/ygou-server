package com.sy.bishe.ygou.mapper;


import com.sy.bishe.ygou.bean.EvaluateBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluateMapper {

    @Insert("insert into tb_evl(evl_user_id,evl_order_id,evl_content,evl_goods_img,evl_goods_name,evl_goods_price,evl_location,evl_time) values(#{evaluate.evl_user_id},#{evaluate.evl_order_id},#{evaluate.evl_content},#{evaluate.evl_goods_img},#{evaluate.evl_goods_name},#{evaluate.evl_goods_price},#{evaluate.evl_location},#{evaluate.evl_time})")
    int addEvaluate(@Param("evaluate") EvaluateBean evaluate);


    @Select("SELECT * FROM tb_evl WHERE evl_order_id = #{parseInt}")
    List<EvaluateBean> getEvaluateByOrderId(int parseInt);


    @Select("SELECT * FROM tb_evl")
    List<EvaluateBean> getEvaluateByHot();


    @Select("SELECT * FROM tb_evl")
    List<EvaluateBean> getEvaluateList();
}
