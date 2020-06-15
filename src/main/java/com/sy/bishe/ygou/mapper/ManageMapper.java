package com.sy.bishe.ygou.mapper;


import com.sy.bishe.ygou.bean.AdminBean;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ManageMapper {

    @Select("select * from tb_adm where adm_name = #{name} ")
    AdminBean getAdminByName(String name);
}
