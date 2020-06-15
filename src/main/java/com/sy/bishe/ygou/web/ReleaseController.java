package com.sy.bishe.ygou.web;


import com.alibaba.fastjson.JSONObject;
import com.sy.bishe.ygou.bean.JsonResult;
import com.sy.bishe.ygou.bean.MyReleaseBean;
import com.sy.bishe.ygou.bean.ReleaseBean;
import com.sy.bishe.ygou.bean.UserBean;
import com.sy.bishe.ygou.service.ReleaseService;
import com.sy.bishe.ygou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController


public class ReleaseController {
    @Autowired
    ReleaseService releaseService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "release/add",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> addReleaseBean(@RequestBody ReleaseBean releaseBean){

        JsonResult jsonResult = new JsonResult();
        System.out.println(releaseBean.toString());
        int user_id = releaseBean.getUser_id();
        UserBean user = userService.getUserBeanById(user_id);
        releaseBean.setRelease_user_img(user.getUser_img());
        try {
            int isOk = releaseService.addReleaseBean(releaseBean);
            if (isOk==1){
                jsonResult.setStatus("ok");
                System.out.println("OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setStatus("exception");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @GetMapping("release/query")
    public ResponseEntity<JsonResult> getReleaseList(){
        JsonResult jsonResult = new JsonResult();
        try {
            List<ReleaseBean> list = releaseService.getReleaseList();
            jsonResult.setData(list);
            System.out.println("用多少求购"+list.size());
            jsonResult.setStatus("ok");
            return ResponseEntity.ok(jsonResult);
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }


    @GetMapping("release/queryById/{id}")
    public ResponseEntity<JsonResult> getReleaseById(@PathVariable (value = "id") Integer id){
        JsonResult jsonResult = new JsonResult();
        try {
            ReleaseBean releaseBean= releaseService.getReleaseById(id);

            if (releaseBean!=null){
                jsonResult.setData(releaseBean);
                int user_id = releaseBean.getUser_id();
                UserBean userBeanById = userService.getUserBeanById(user_id);

                String user_name = userBeanById.getUser_name();

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userName",user_name);
                jsonResult.setJsonObject(jsonObject);
                System.out.println("用多少求购"+releaseBean.getUser_id());
                jsonResult.setStatus("ok");
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }

    @GetMapping("release/delete/{id}")
    public ResponseEntity<JsonResult> DeleteReleaseById(@PathVariable (value = "id") Integer id){
        JsonResult jsonResult = new JsonResult();
        try {
           int result = releaseService.DeleteReleaseById(id);
            if (result!=0){
                jsonResult.setStatus("ok");
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }



    @RequestMapping(value = "release/update",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> updateReleaseBean(@RequestBody ReleaseBean releaseBean){

        JsonResult jsonResult = new JsonResult();
        System.out.println(releaseBean.toString());
        int release_id = releaseBean.getId();
        try {
            int isOk = releaseService.updateReleaseBean(releaseBean);
            if (isOk==1){
                jsonResult.setStatus("ok");
                System.out.println("OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setStatus("exception");
        }
        return ResponseEntity.ok(jsonResult);
    }
}
