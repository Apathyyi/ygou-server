package com.sy.bishe.ygou.service.impl;

import com.sy.bishe.ygou.bean.FriendBean;
import com.sy.bishe.ygou.mapper.FriendMapper;
import com.sy.bishe.ygou.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendMapper friendMapper;
    @Override
    public List<FriendBean> getFriendListById(Integer id) {
        return friendMapper.getFriendListById(id);
    }

    @Override
    public int addFriendRequest(Integer friend_id, Integer id) {
        return friendMapper.addFriendRequest(friend_id,id);
    }

    @Override
    public int add(FriendBean friendBean) {

        return friendMapper.add(friendBean);
    }

    @Override
    public int addFriendAgree(Integer friend_id, Integer id) {
        return friendMapper.addFriendAgree(friend_id,id);
    }

    @Override
    public int updateToAgree(Integer friend_id, Integer id) {

        return friendMapper.updateToAgree(friend_id,id);
    }
}
