package com.sy.bishe.ygou.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sy.bishe.ygou.bean.FriendBean;
import com.sy.bishe.ygou.bean.JsonResult;
import com.sy.bishe.ygou.bean.UserBean;
import com.sy.bishe.ygou.service.FriendService;
import com.sy.bishe.ygou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "friend/query/{id}")
    public ResponseEntity<JsonResult> getFriendListById(@PathVariable(value = "id") Integer id){
        JsonResult jsonResult = new JsonResult();
        try {
            List<UserBean> friends = new ArrayList<>();
            List<FriendBean> list= friendService.getFriendListById(id);
            System.out.println(list.size());
            if (!list.isEmpty()){
                for (int i = 0; i < list.size(); i++) {
                    FriendBean friendBean = list.get(i);
                    System.out.println(friendBean.toString());
                    if (friendBean.getFriend_tag().equals("friend")&&friendBean.isFriend_agree()){
                       int friendId = friendBean.getFriend_id();
                        UserBean userBean= userService.getUserBeanById(friendId);
                        friends.add(userBean);
                    }
                }
            }
            jsonResult.setStatus("ok");
            jsonResult.setData(friends);

        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setStatus("error");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @RequestMapping(value = "friend/notify/{id}")
    public ResponseEntity<JsonResult> getFriendNotify(@PathVariable(value = "id") Integer id){
        JsonResult jsonResult = new JsonResult();
        try {
            List<FriendBean> notify = new ArrayList<>();
            List<FriendBean> recommend = new ArrayList<>();
            List<FriendBean> list = friendService.getFriendListById(id);
            if (!list.isEmpty()){
                for (FriendBean friendBean:list){
                    if (friendBean.getFriend_tag().equals("notify")){
                        UserBean userBeanById = userService.getUserBeanById(friendBean.getFriend_id());
                        friendBean.setFriend_name(userBeanById.getUser_name());
                        friendBean.setFriend_img(userBeanById.getUser_img());
                        notify.add(friendBean);
                    }else if (friendBean.getFriend_tag().equals("recommend")){
                        UserBean userBeanById = userService.getUserBeanById(friendBean.getFriend_id());
                        friendBean.setFriend_name(userBeanById.getUser_name());
                        friendBean.setFriend_img(userBeanById.getUser_img());
                        recommend.add(friendBean);
                    }
                }
                jsonResult.setStatus("ok");
                jsonResult.setData(notify);
               JSONObject jsonObject = new JSONObject();
               jsonObject.put("recommend",recommend);
               jsonResult.setJsonObject(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setStatus("error");
        }
        return ResponseEntity.ok(jsonResult);
    }


    //添加请求
    @RequestMapping(value = "friend/updateToAdd/{id}")
    public ResponseEntity<JsonResult> addFriendRequest(@PathVariable(value = "id") Integer id, @RequestBody Integer friend_id){

        System.out.println("请求添加的好友id是"+friend_id);
        FriendBean friendBean = new FriendBean();
        friendBean.setUser_id(friend_id);
        friendBean.setFriend_id(id);
        friendBean.setFriend_tag("notify");
        friendBean.setFriend_agree(false);
        JsonResult jsonResult = new JsonResult();
        //修改为已添加

        try {
            int i = friendService.addFriendRequest(friend_id, id);
            if (i!=0){
                System.out.println("修改成功");
                //增加朋友请求
                int result = friendService.add(friendBean);
                if (result!=0){
                    jsonResult.setStatus("ok");
                }
            }else {
                System.out.println("修改失败");
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);

    }

    //同意请求
    @RequestMapping(value = "friend/addAgree/{id}")
    public ResponseEntity<JsonResult> addFriendAgree(@PathVariable(value = "id") Integer id, @RequestBody Integer friend_id){

        System.out.println("请求添加的好友id是"+friend_id+"当前用户id"+id);
        FriendBean friendBean = new FriendBean();
        friendBean.setUser_id(friend_id);
        friendBean.setFriend_id(id);
        friendBean.setFriend_tag("friend");
        friendBean.setFriend_agree(true);
        JsonResult jsonResult = new JsonResult();
        //修改为已同意
        try {
            int i = friendService.updateToAgree(friend_id, id);
            if (i!=0){
                System.out.println("修改成功");
                //增加朋友请求 两边增加
                FriendBean anotherBean = new FriendBean();
                anotherBean.setUser_id(id);
                anotherBean.setFriend_id(friend_id);
                anotherBean.setFriend_tag("friend");
                anotherBean.setFriend_agree(true);
                int result = friendService.add(friendBean);
                int anotherresult = friendService.add(anotherBean);
                if (result!=0&&anotherresult!=0){
                    jsonResult.setStatus("ok");
                }
            }else {
                System.out.println("修改失败");
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);

    }
}
