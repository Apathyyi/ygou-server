package com.sy.bishe.ygou.mapper;

import com.sy.bishe.ygou.bean.UserBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

// @Mapper 这里可以使用@Mapper注解，但是每个mapper都加注解比较麻烦，
// 所以统一配置@MapperScan在扫描路径在application类中
@Repository
public interface UserMapper {
    @Select("SELECT * FROM tb_user WHERE user_id = #{id}")
    UserBean getUserBeanById(int id);

    //查全部
    @Select("SELECT * FROM tb_user")
    public List<UserBean> getUserList();

    //增
    @Insert("INSERT into tb_user(user_name,user_word,user_password,user_email,user_phone,user_address,user_age,user_birth,user_gender,user_school,user_specialty,user_grade,user_stunumber,user_isidentificated,user_balance,user_img,user_college,user_signature,user_paynum) values(#{user.user_name},#{user.user_word},#{user.user_password},#{user.user_email},#{user.user_phone},#{user.user_address},#{user.user_age},#{user.user_birth},#{user.user_gender},#{user.user_school},#{user.user_specialty},#{user.user_grade},#{user.user_stunumber},#{user.user_isidentificated},#{user.user_balance},#{user.user_img},#{user.user_college},#{user.user_signature},#{user_paynum})")
    public int add(@Param("user") UserBean user);

    //删
    @Delete("delete from tb_user where user_id=#{id}")
    public int delete(Integer id);

    //改
    @Update("update tb_user set user_name=#{user.user_name}," +
           " user_word = #{user.user_word},user_password=#{user.user_password},user_email=#{user.user_email},user_phone=#{user.user_phone},"+
            "user_address=#{user.user_address},user_age=#{user.user_age},user_birth=#{user.user_birth},user_gender=#{user.user_gender},"+
            "user_school=#{user.user_school},user_specialty=#{user.user_specialty},user_grade=#{user.user_grade},"+
            "user_stunumber=#{user.user_stunumber},user_isidentificated=#{user.user_isidentificated},user_balance=#{user.user_balance},user_img=#{user.user_img}"+
            ",user_college=#{user.user_college},user_signature=#{user.user_signature} ,user_paynum = #{user.user_paynum} where user_id=#{id}")
    public int update(@Param("id") Integer id, @Param("user") UserBean user);

    @Select("SELECT * FROM tb_user WHERE user_name = #{user_name}")
    public List<UserBean> getUserBeanByName(String user_name);

    @Select("SELECT * FROM tb_user WHERE user_phone = #{user_phone}")
    List<UserBean> getUserBeanByPhone(String user_phone);

    @Select("SELECT user_balance FROM tb_user WHERE user_id = #{id}")
    Double getUserBalanceById(Integer id);

    @Update("UPDATE tb_user SET user_balance =user_balance-#{price} WHERE user_id = #{id}")
    public int updateUserBalance(Double price,Integer id);

    @Update("UPDATE tb_user SET user_balance =user_balance+#{price} WHERE user_name = 'admin'")
    int updateBalanceAdmin(Double price);

    @Update("UPDATE tb_user SET user_img =#{originalFilename} WHERE user_id = #{id}")
    int updateUserImg(Integer id, String originalFilename);

    @Update("UPDATE tb_user SET user_balance = user_balance + #{price} WHERE user_id = #{id}")
    int addUserBalance(Double valueOf, Integer id);

    @Update("UPDATE tb_user SET user_paynum = #{num} WHERE user_id = #{id}")
    int updateUserPayNum(String num,Integer id);

    @Update("UPDATE tb_user SET user_password=#{pass} WHERE user_phone = #{phone}")
    int updatePassByPhone(String pass, String phone);

    @Select("SELECT * FROM tb_user WHERE user_name like CONCAT('%',#{name},'%') ")
    List<UserBean> getUserListLikeName(String name);
}