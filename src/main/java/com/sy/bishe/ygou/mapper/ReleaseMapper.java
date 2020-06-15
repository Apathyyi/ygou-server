package com.sy.bishe.ygou.mapper;


import com.sy.bishe.ygou.bean.MyReleaseBean;
import com.sy.bishe.ygou.bean.ReleaseBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReleaseMapper {


    @Insert("INSERT INTO tb_release(release_user_id,release_type,release_title,release_desc,release_count,release_price,release_contact,release_user_img,release_time) " +
            "VALUES(#{releaseBean.user_id},#{releaseBean.release_type},#{releaseBean.release_title},#{releaseBean.release_desc}," +
            "#{releaseBean.release_count},#{releaseBean.release_price},#{releaseBean.release_contact},#{releaseBean.release_user_img},#{releaseBean.release_time})")

    int addReleaseBean(@Param(value = "releaseBean") ReleaseBean releaseBean);

    @Select("SELECT * FROM tb_release WHERE release_user_id=#{id}")
    List<ReleaseBean> getUserBuyListById(Integer id);

    @Select("SELECT * FROM tb_release")
    List<ReleaseBean> getReleaseList();

    @Select("SELECT * FROM tb_release where release_id=#{id}")
    ReleaseBean getReleaseById(Integer id);

    @Delete("delete from tb_release where release_id=#{id}")
    int DeleteReleaseById(Integer id);

    @Update("update tb_release  set release_type = #{releaseBean.release_type} ,release_title = #{releaseBean.release_title}," +
            "release_desc=#{releaseBean.release_desc},release_count=#{releaseBean.release_count},release_price=#{releaseBean.release_price}," +
            "release_contact=#{releaseBean.release_contact},release_time=#{releaseBean.release_time}" )
    int updateReleaseBean(@Param("releaseBean") ReleaseBean releaseBean);
}
