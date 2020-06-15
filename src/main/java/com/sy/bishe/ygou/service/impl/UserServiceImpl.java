package com.sy.bishe.ygou.service.impl;

import com.sy.bishe.ygou.bean.UserBean;
import com.sy.bishe.ygou.mapper.UserMapper;
import com.sy.bishe.ygou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserBean getUserBeanById(Integer id) {
        return userMapper.getUserBeanById(id);
    }
    @Override
    public List<UserBean> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public int add(UserBean user) {
        return userMapper.add(user);
    }

    @Override
    public int delete(Integer id) {
        return userMapper.delete(id);
    }

    @Override
    public int update(Integer id, UserBean user) {
        return userMapper.update(id,user);
    }

    @Override
    public List<UserBean> getUserBeanByName(String user_name) {
        return userMapper.getUserBeanByName(user_name);
    }

    @Override
    public List<UserBean> getUserBeanByPhone(String user_phone) {
        return userMapper.getUserBeanByPhone(user_phone);
    }

    @Override
    public Double getUserBalanceById(Integer id) {
        return userMapper.getUserBalanceById(id);
    }

    @Override
    public int updateUserBalance(Double price,Integer id) {
        return userMapper.updateUserBalance(price,id);
    }

    @Override
    public int updateBalanceAdmin(Double price) {
        return userMapper.updateBalanceAdmin(price);
    }

    @Override
    public int updateUserImg(Integer id, String originalFilename) {
        return userMapper.updateUserImg(id,originalFilename);
    }

    @Override
    public int addUserBalance(Double valueOf, Integer id) {
        return userMapper.addUserBalance(valueOf,id);
    }

    @Override
    public int updateUserPayNum(String num,Integer id) {
        return userMapper.updateUserPayNum(num,id);
    }

    @Override
    public int updatePassByPhone(String pass, String phone) {
        return userMapper.updatePassByPhone(pass,phone);
    }

    @Override
    public List<UserBean> getUserListLikeName(String name) {
        return userMapper.getUserListLikeName(name);
    }
}
