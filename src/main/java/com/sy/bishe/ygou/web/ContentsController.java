package com.sy.bishe.ygou.web;


import com.sy.bishe.ygou.bean.ContentsBean;
import com.sy.bishe.ygou.bean.JsonResult;
import com.sy.bishe.ygou.service.ContentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContentsController {

    @Autowired
    ContentsService contentsService;

    @RequestMapping(value = "contents/queryBuOrderId/{id}")
    public ResponseEntity<JsonResult> getContentsByOrderId(@PathVariable(value = "id") Integer id){
        System.out.println("获取订单评价的评论"+id);
        JsonResult jsonResult = null;
        try {
            jsonResult = new JsonResult();
            List<ContentsBean> list = contentsService.getContentsByOrderId(id);
            if (list!=null){
                jsonResult.setStatus("ok");
                jsonResult.setData(list);
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }

        return ResponseEntity.ok(jsonResult);

    }

    @RequestMapping(value = "contents/add",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> addContent(@RequestBody ContentsBean contentsBean){

        JsonResult jsonResult = new JsonResult();

        try {
            int result = contentsService.addContent(contentsBean);
            if (result!=0){
                jsonResult.setStatus("ok");
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);

    }
}
