package com.sy.bishe.ygou.mapper;

import com.sy.bishe.ygou.bean.SortVerticalBean;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SortVerticalMapper  {
    @Select("SELECT * FROM tb_vertical")
    public List<SortVerticalBean> getSortVertical();
}
