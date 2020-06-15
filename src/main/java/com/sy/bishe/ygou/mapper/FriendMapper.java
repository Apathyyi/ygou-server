package com.sy.bishe.ygou.mapper;

import com.sy.bishe.ygou.bean.FriendBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendMapper {

    @Select("SELECT * FROM tb_friend WHERE user_id=#{id}")
    List<FriendBean> getFriendListById(Integer id);


    @Update("UPDATE tb_friend set friend_agree = 1 WHERE friend_id = #{friend_id} AND user_id = #{id} ")
    int addFriendRequest(Integer friend_id, Integer id);


    @Insert("Insert into tb_friend(friend_id,user_id,friend_tag,friend_agree) values(#{friendBean.friend_id},#{friendBean.user_id},#{friendBean.friend_tag},#{friendBean.friend_agree})")
    int add(@Param("friendBean") FriendBean friendBean);

    @Update("UPDATE tb_friend set friend_agree = 1 WHERE friend_id = #{friend_id} AND user_id = #{id} ")
    int addFriendAgree(Integer friend_id, Integer id);

    @Update("UPDATE tb_friend set friend_agree = 1 WHERE friend_id = #{friend_id} AND user_id = #{id} ")
    int updateToAgree(Integer friend_id, Integer id);
}
