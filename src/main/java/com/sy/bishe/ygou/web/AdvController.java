package com.sy.bishe.ygou.web;

import com.alibaba.fastjson.JSONObject;
import com.sy.bishe.ygou.bean.AdvBean;
import com.sy.bishe.ygou.bean.JsonResult;
import com.sy.bishe.ygou.bean.UserBean;
import com.sy.bishe.ygou.service.AdvService;
import com.sy.bishe.ygou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdvController {

    @Autowired
    private AdvService advService;

    @Autowired
    UserService userService;
    @RequestMapping(value = "adv/queryRandom/{id}")
    public ResponseEntity<JsonResult> getAdvList(@PathVariable(value = "id") Integer id){
        JsonResult result = new JsonResult();
        try {
            UserBean userBeanById = userService.getUserBeanById(id);
            String user_img = userBeanById.getUser_img();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user_img",user_img);
            result.setJsonObject(jsonObject);
            List<AdvBean> list = advService.getAdvList();
            result.setCode("0");
            result.setStatus("ok");
            result.setData(list);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
