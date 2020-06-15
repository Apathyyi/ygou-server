package com.sy.bishe.ygou.web;

import com.alibaba.fastjson.JSONObject;
import com.sy.bishe.ygou.bean.*;
import com.sy.bishe.ygou.service.CartService;
import com.sy.bishe.ygou.service.GoodsInfoService;
import com.sy.bishe.ygou.service.OrderService;
import com.sy.bishe.ygou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private GoodsInfoService goodsInfoService;

    @Autowired
    private UserService userService;

    /**
     * 新增订单
     * @param id
     * @param list
     * @return
     */
    @RequestMapping(value = "order/add/{id}",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> add(@PathVariable(value = "id") Integer id, @RequestBody List<Integer> list){
        boolean isOk = true;
        System.out.println("用户ID"+id);
        JsonResult jsonResult = new JsonResult();
        try {

            int size = list.size();
            for (int i = 0; i <size ; i++) {
                int goods_id = list.get(i);
                CartBean cartBean = cartService.getGoodsByGoodsId(goods_id);
                System.out.println("-------------"+goods_id);
                GoodsInfoBean goodsInfoBean = goodsInfoService.getGoodsInfoById(goods_id);
                System.out.println("商品信息"+goodsInfoBean);

                UserBean userBean = userService.getUserBeanById(id);

                System.out.println("用户信息"+userBean+userBean.getUser_img());

                OrderBean orderBean = new OrderBean();
                orderBean.setOrder_user_id(id);
                orderBean.setOrder_count(size);
                orderBean.setOrder_goods_id(goods_id);
                orderBean.setOrder_price(cartBean.getCart_price());
                orderBean.setOrder_thumb(cartBean.getCart_thumb());
                orderBean.setOrder_title(cartBean.getCart_name());
                orderBean.setOrder_tag("待发货");
                orderBean.setOrder_goods_releaseimg(userBean.getUser_img());
                orderBean.setOrder_goods_releasename(userBean.getUser_name());
                orderBean.setOrder_goods_lable(goodsInfoBean.getGoodsinfo_lable());
                orderBean.setOrder_trans_cast("12.0");
                orderBean.setOrder_trans_count("1");
                orderBean.setOrder_total_count(cartBean.getCart_goods_count());
                orderBean.setOrder_total_price(cartBean.getCart_price() * cartBean.getCart_goods_count());

                SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String date = sDateFormat.format(new java.util.Date());
                System.out.println("当前时间为："+date);
                orderBean.setOrder_time(date);
                //创建订单
                int add = orderService.add(orderBean);
                if (add == 0){
                   isOk = false;
                }else {
                    //删除商品
                    goodsInfoService.deleteGoodsById(goods_id);
                }
            }
            if (isOk){
                jsonResult.
                        setStatus("ok");

            }else {
                jsonResult.setStatus("error");
            }

        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }

    /**
     * 查询订单
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value = "order/query/{id}",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> getOrderListByUserId(@PathVariable(value = "id") Integer id,@RequestBody String type){

        System.out.println("传入类型"+type+"传入ID"+id);
        JsonResult jsonResult = new JsonResult();
        String tag = "";
        try {
            if (type.equals("all")){
                List<OrderBean> list = orderService.getOrderListByUserId(id);
                jsonResult.setData(list);
            }else {
                if (type.equals("wait")){
                    tag = "待发货";
                }else if (type.equals("receive")){
                    tag = "待收货";
                }else if (type.equals("evaluate")){
                    tag = "待评价";
                }else if (type.equals("hasevl")){
                    tag = "已评价";
                }
                List<OrderBean> list = orderService.getOrderListByUserIdAndType(id,tag);
                jsonResult.setData(list);
            }
                jsonResult.setStatus("ok");
        } catch (Exception e) {
            jsonResult.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }

    /**
     * 删除订单
     * @param id
     * @return
     */
    @RequestMapping(value = "order/delete/{id}")
    public ResponseEntity<JsonResult> deleteOrderById(@PathVariable(value = "id") Integer id){
        JsonResult jsonResult = new JsonResult();

        try {
            int result = orderService.deleteOrderById(id);
            if (result!=0){
                jsonResult.setStatus("ok");
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }

    @RequestMapping(value = "order/updateTagToSendById/{id}")
    public ResponseEntity<JsonResult> updateTagToSendById(@PathVariable(value = "id") Integer id){

        System.out.println("修改发货"+id);
        JsonResult jsonResult = new JsonResult();
        try {
            int result = orderService.updateTagToSendById(id);
            if (result!=0){
                System.out.println("成功");
                jsonResult.setStatus("ok");
            }else {
                System.out.println("失败");
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return  ResponseEntity.ok(jsonResult);
    }


    @RequestMapping(value = "order/updateTagToReceiveById/{id}")
    public ResponseEntity<JsonResult> updateTagToReceiveById(@PathVariable(value = "id") Integer id){

        System.out.println("修改收获"+id);
        JsonResult jsonResult = new JsonResult();
        try {
            int result = orderService.updateTagToReceiveById(id);
            if (result!=0){
                System.out.println("成功");
                jsonResult.setStatus("ok");
            }else {
                System.out.println("失败");
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return  ResponseEntity.ok(jsonResult);
    }

    @GetMapping(value = "order/query/{id}")
    public ResponseEntity<JsonResult> getOrderById(@PathVariable(value = "id") String id){

        int order_id = Integer.parseInt(id);
        System.out.println("传入的订单id"+id);
        JsonResult jsonResult = new JsonResult();
        try {
            OrderBean orderBean = orderService.getOrderById(order_id);
            if (orderBean!=null){
                System.out.println("订单详情"+orderBean.toString());
                jsonResult.setStatus("ok");
                jsonResult.setData(orderBean);
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }

    @RequestMapping(value = "order/updateTagToEvlById/{id}")
    public ResponseEntity<JsonResult> updateTagToEvlById(@PathVariable(value = "id") Integer id){

        JsonResult jsonResult = new JsonResult();
        try {
            int result = orderService.updateTagToEvlById(id);
            if (result!=0){
                jsonResult.setStatus("ok");
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return  ResponseEntity.ok(jsonResult);
    }



}
