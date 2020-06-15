package com.sy.bishe.ygou.mapper;


import com.sy.bishe.ygou.bean.ContentsBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentsMapper {


    @Select("SELECT * FROM tb_contents WHERE contents_order_id =#{id}")
    List<ContentsBean> getContentsByOrderId(Integer id);

    @Insert("INSERT INTO tb_contents(contents_name,contents_to_name,contents_text,contents_order_id) VALUES(#{contentsBean.contents_name},#{contentsBean.contents_to_name},#{contentsBean.contents_text},#{contentsBean.contents_order_id})")
    int addContent(@Param("contentsBean") ContentsBean contentsBean);
}
