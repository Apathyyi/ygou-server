package com.sy.bishe.ygou.mapper;


import com.sy.bishe.ygou.bean.PicturesBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PicturesMapper  {

    @Select("SELECT * FROM tb_pictures WHERE pictures_id = #{id}")
    PicturesBean getPicturesById(int id);

    //查全部
    @Select("SELECT * FROM tb_pictures")
    public List<PicturesBean> getPicturesList();

    //增
    @Insert("insert into tb_pictures(pictures_id, pictures_url, pictures_goodsid,pictures_tag) values(#{pictures_id},#{pictures_url},#{pictures_goodsid},#{pictures_tag})")
    public int add(PicturesBean picturesBean);

    //删
    @Delete("delete from tb_pictures where pictures_id=#{id}")
    public int delete(Integer id);

    @Select("SELECT * FROM tb_pictures where pictures_goodsid=#{id}")
    List<PicturesBean> getPicturesListByGoodsId(Integer id);
}
