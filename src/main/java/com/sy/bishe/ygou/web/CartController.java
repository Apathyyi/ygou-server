package com.sy.bishe.ygou.web;

import com.alibaba.fastjson.JSONObject;
import com.sy.bishe.ygou.bean.CartBean;
import com.sy.bishe.ygou.bean.GoodsInfoBean;
import com.sy.bishe.ygou.bean.JsonResult;
import com.sy.bishe.ygou.service.CartService;
import com.sy.bishe.ygou.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService service;

    @Autowired
    private GoodsInfoService goodsInfoService;

    /**
     * 每个用户的购物车
     * @param id
     * @return
     */
    @GetMapping(value = "cart/queryList/{id}")
    public List<CartBean> getGoodsIdByUserId(@PathVariable(value = "id") Integer id){
        return service.getGoodsIdByUserId(id);
    }

    /**
     * 购物车界面增加数量
     * @param id
     * @return
     */
    @RequestMapping(value = "cart/add/{id}",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> addCartByGoodsId(@PathVariable(value = "id") Integer id){
        JsonResult jsonResult = new JsonResult();
        int isOk = service.addCartByGoodsId(id);
        if (isOk == 0){
            //更新失败
            jsonResult.setStatus("error");
        }
        else {
            jsonResult.setStatus("ok");
        }
        return ResponseEntity.ok(jsonResult);
    }

    /**
     * 减少
     * @param id
     * @return
     */
    @RequestMapping(value = "cart/sub/{id}",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> subCartByGoodsId(@PathVariable(value = "id") Integer id) {
        JsonResult jsonResult = new JsonResult();
        int isOk = service.subCartByGoodsId(id);
        if (isOk == 0){
            //更新失败
            jsonResult.setStatus("error");
        }
        else {
            jsonResult.setStatus("ok");
        }
        return ResponseEntity.ok(jsonResult);
    }

    /**
     * 删除
     * @param list
     * @return
     */
    @RequestMapping(value = "cart/deleteByIds" ,method = RequestMethod.POST)
    public ResponseEntity<JsonResult> deleteCartGoods(@RequestBody List<Integer> list){
        JsonResult jsonResult = new JsonResult();
        System.out.println("传递过来的商品id集合"+list.toString());
        boolean allOk = true;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int id = list.get(i);
            int isOk = service.deleteCartGoods(id);
            if (isOk == 0){
                allOk = false;
            }
        }
        if (allOk){
            jsonResult.setStatus("ok");
        }else {
            jsonResult.setStatus("error");
        }
        return ResponseEntity.ok(jsonResult);
    }

    /**
     * 添加商品到购物车
     */
    @RequestMapping(value = "cart/addGoodsId/{id}",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> addGoodsToCart(@PathVariable(value = "id") Integer id, @RequestBody JSONObject jsonObject){
        System.out.println(jsonObject.toJSONString());
        Integer user_id = jsonObject.getInteger("user_id");
        JsonResult jsonResult = new JsonResult();
        boolean isHad = false;
        int isOk = 0;
        //获取用户的购物车
        System.out.println(user_id);
        List<CartBean> cartBeans = service.getGoodsIdByUserId(user_id);
        System.out.println("goodsIdByUserId:"+cartBeans.isEmpty());
        for (CartBean c:cartBeans) {
            int goodsId = c.getCart_goods_id();
            System.out.println("GOODSID:"+id+"CART_GOODSID"+goodsId);
            if (goodsId == id){
                isHad = true;
                //已存在该商品 数量+1
                isOk = service.addCartByGoodsId(goodsId);
            }
        }
        //不存在该商品 增加到购物车 数量为1
        if (!isHad){
            GoodsInfoBean goodsInfoBean = goodsInfoService.getGoodsInfoById(id);
            CartBean cartBean = new CartBean();
            cartBean.setCart_user_id(user_id);
            cartBean.setCart_goods_id(id);
            cartBean.setCart_goods_count(1);
            cartBean.setCart_name(goodsInfoBean.getGoodsinfo_name());
            cartBean.setCart_desc(goodsInfoBean.getGoodsinfo_desc());
            cartBean.setCart_price(goodsInfoBean.getGoodsinfo_price());
            cartBean.setCart_thumb(goodsInfoBean.getGoodsinfo_thumb());
            isOk = service.addGoodsToCart(cartBean);
        }
        //添加失败
        if (isOk == 0){
            jsonResult.setStatus("error");
        }else {
            jsonResult.setStatus("ok");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @RequestMapping(value = "cart/deleteAll/{id}")
    public ResponseEntity<JsonResult> deleteCartByUserId(@PathVariable(value = "id") Integer id){

        JsonResult jsonResult = new JsonResult();
        int isOk = service.deleteCartByUserId(id);

        if (isOk == 0){
            jsonResult.setStatus("error");
        }else {
            jsonResult.setStatus("ok");
        }
        return ResponseEntity.ok(jsonResult);
    }
}
