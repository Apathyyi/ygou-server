package com.sy.bishe.ygou.mapper;

import com.sy.bishe.ygou.bean.JsonResult;
import com.sy.bishe.ygou.bean.SortContentBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SortContentMapper {
    @Select("SELECT * FROM tb_content WHERE content_verticalid=#{id}")
    public List<SortContentBean> getSortContent(Integer id);

    @Select("SELECT content_id FROM tb_content WHERE content_section=#{brand}")
    int getBrandIdByName(String brand);



    @Select({
            "<script>",
            "select brand_id",
            "from tb_brand",
            "where brand_sortId in",
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    List<Integer> getSortContentByIds(@Param("ids") List<Integer> ids);
}
