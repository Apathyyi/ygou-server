package com.sy.bishe.ygou.service;


import com.sy.bishe.ygou.bean.FriendBean;

import java.util.List;

public interface FriendService {

    List<FriendBean> getFriendListById(Integer id);

    int addFriendRequest(Integer friend_id, Integer id);

    int add(FriendBean friendBean);

    int addFriendAgree(Integer friend_id, Integer id);

    int updateToAgree(Integer friend_id, Integer id);
}
