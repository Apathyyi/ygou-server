package com.sy.bishe.ygou.mapper;

import com.sy.bishe.ygou.bean.AddressBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressMapper {

    @Select("SELECT * FROM tb_address WHERE address_user_id = #{id}")
    public List<AddressBean> getAddressList(@Param("id") Integer id);

    @Delete("DELETE FROM tb_address WHERE address_id = #{id}")
    public int deleteAddressById(@Param(value = "id") Integer id);

    @Insert({"INSERT INTO tb_address(address_user_id,address_name,address_phone,address_content,address_default,address_area) VALUES(#{address.address_user_id}," +
            "#{address.address_name},#{address.address_phone},#{address.address_content},#{address.address_default},#{address.address_area})"})
    public int add(@Param("address") AddressBean address);
}
