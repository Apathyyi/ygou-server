package com.sy.bishe.ygou.service;

import com.sy.bishe.ygou.bean.UserBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.ComponentScans;

import java.util.List;

public interface UserService {

    UserBean getUserBeanById(Integer id);

    public List<UserBean> getUserList();

    public int add(UserBean user);

    public int delete(Integer id);

    public int update(Integer id, UserBean user);

    public List<UserBean> getUserBeanByName(String user_name);

    public List<UserBean> getUserBeanByPhone(String user_phone);

    public Double getUserBalanceById(Integer id);

    public int updateUserBalance(Double price,Integer id);

    int updateBalanceAdmin(Double price);

    int updateUserImg(Integer id, String originalFilename);

    int addUserBalance(Double valueOf, Integer id);

    int updateUserPayNum(String num,Integer id);

    int updatePassByPhone(String pass, String phone);

    List<UserBean> getUserListLikeName(String name);

}
