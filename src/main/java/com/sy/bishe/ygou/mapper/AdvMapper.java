package com.sy.bishe.ygou.mapper;


import com.sy.bishe.ygou.bean.AdvBean;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvMapper {

    @Select("SELECT * FROM tb_adv")
    List<AdvBean> getAdvList();
}
