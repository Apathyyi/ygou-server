package com.sy.bishe.ygou.web;


import com.alibaba.fastjson.JSONObject;
import com.sy.bishe.ygou.bean.ContentsBean;
import com.sy.bishe.ygou.bean.EvaluateBean;
import com.sy.bishe.ygou.bean.JsonResult;
import com.sy.bishe.ygou.bean.UserBean;
import com.sy.bishe.ygou.service.ContentsService;
import com.sy.bishe.ygou.service.EvaluateService;
import com.sy.bishe.ygou.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
public class EvaluateController {

@Autowired
private EvaluateService evaluateService;

@Autowired
    UserService userService;

@Autowired
    ContentsService contentsService;


    @RequestMapping(value = "evaluate/add",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> addEvaluate(@RequestBody EvaluateBean evaluateBean){
        JsonResult jsonResult = new JsonResult();
        System.out.println("评论内容是："+evaluateBean.toString());
        try {
            int result = evaluateService.addEvaluate(evaluateBean);
            if (result!=0){
                jsonResult.setStatus("ok");
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }

    @RequestMapping(value = "evaluate/queryByOrderId/{id}")
    public ResponseEntity<JsonResult> getEvaluateByOrderId(@PathVariable(value = "id") String id){

        JsonResult jsonResult = new JsonResult
                ();
        List<EvaluateBean> evaluateBeans = evaluateService.getEvaluateByOrderId(Integer.parseInt(id));
        if (evaluateBeans!=null){
            EvaluateBean evaluateBean = evaluateBeans.get(0);
            int evl_user_id = evaluateBean.getEvl_user_id();
            UserBean userBeanById = userService.getUserBeanById(evl_user_id);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user",userBeanById);
            jsonObject.put("comments","");
            jsonResult.setData(evaluateBean);
            jsonResult.setJsonObject(jsonObject);
            jsonResult.setStatus("ok");
        }
        return ResponseEntity.ok(jsonResult);
    }





    @RequestMapping(value = "evaluate/queryHot")
    public ResponseEntity<JsonResult> getEvaluateByHot(){

        JsonResult jsonResult = new JsonResult();
        try {
            List<EvaluateBean> evaluateBeans = evaluateService.getEvaluateByHot();
            System.out.println("晒单总的条数");
            List<List<ContentsBean>> contentsList = new ArrayList<>();
            List<List<ContentsBean>> contentsLisHot= new ArrayList<>();
            if (evaluateBeans!=null){
                for (EvaluateBean e:evaluateBeans
                     ) {
                    int evl_order_id = e.getEvl_order_id();
                    //获得订单评论
                    List<ContentsBean> contentsByOrderId = contentsService.getContentsByOrderId(evl_order_id);
                    System.out.println("对应评论总条数"+contentsByOrderId.size());
                    contentsList.add(contentsByOrderId);
                }
                System.out.println("共有多少个晒单");
                contentsList.sort(new Comparator<List<ContentsBean>>() {
                    @Override
                    public int compare(List<ContentsBean> contentsBeans, List<ContentsBean> t1) {
                        return t1.size()-contentsBeans.size();
                    }
                });
               if (contentsList.size()>3){
                   for (int i =0;i<3;i++ ){
                       contentsLisHot.add(contentsList.get(i));
                   }
               }
               else {
                   contentsLisHot.addAll(contentsList);
               }
               System.out.println("最火的评论条数"+contentsLisHot.size());
               List<EvaluateBean> listHotThree = new ArrayList<>();
               if (!contentsLisHot.isEmpty()){
                   for (int i =0;i<contentsLisHot.size();i++){
                       List<ContentsBean> list = contentsLisHot.get(i);
                       if (!list.isEmpty()){
                           int contents_order_id = list.get(0).getContents_order_id();
                           //得到评论最多的三条评价
                           List<EvaluateBean> evaluateByOrderId = evaluateService.getEvaluateByOrderId(contents_order_id);
                           int evl_user_id = evaluateByOrderId.get(0).getEvl_user_id();
                           listHotThree.add(evaluateByOrderId.get(0));
                       }
                   }
               }
               System.out.println("最火的三条评价"+listHotThree.size());
               jsonResult.setData(listHotThree);
               jsonResult.setStatus("ok");

            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }

    @RequestMapping(value = "evaluate/query")
    public ResponseEntity<JsonResult> getEvaluateList(){

        JsonResult jsonResult = new JsonResult
                ();
        try {
            List<EvaluateBean> evaluateBeans = evaluateService.getEvaluateList();
            List<UserBean> userBeanList = new ArrayList<>();
            if (evaluateBeans!=null){
                for (EvaluateBean e:evaluateBeans
                     ) {
                    int evl_user_id = e.getEvl_user_id();
                    UserBean userBeanById = userService.getUserBeanById(evl_user_id);
                    userBeanList.add(userBeanById);
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("users",userBeanList);
                jsonResult.setData(evaluateBeans);
                jsonResult.setJsonObject(jsonObject);
                jsonResult.setStatus("ok");
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }



}
