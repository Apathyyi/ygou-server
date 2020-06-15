package com.sy.bishe.ygou.mapper;

import com.sy.bishe.ygou.bean.BrandBean;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandMapper {
    @Select("SELECT * FROM tb_brand WHERE brand_sortId = #{id}")
    public List<BrandBean> getBrandById(Integer id);
    @Select("SELECT brand_name FROM tb_brand WHERE brand_id = #{id}")
    String getBrandName(int id);

    @Select("SELECT brand_id FROM tb_brand WHERE brand_name = #{name}")
    List<Integer> getIdByName(String name);

    @Select("SELECT brand_id FROM tb_brand WHERE brand_name = #{name} AND brand_sortId=#{type}")
    int getBrandIdByNameAndId(Integer type, String name);
}
