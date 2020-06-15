package com.sy.bishe.ygou.web;


import com.alibaba.fastjson.JSONObject;
import com.sy.bishe.ygou.bean.GoodsBean;
import com.sy.bishe.ygou.bean.JsonResult;
import com.sy.bishe.ygou.bean.PicturesBean;
import com.sy.bishe.ygou.service.GoodsService;
import com.sy.bishe.ygou.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoodsController  {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private PictureService pictureService;

    @GetMapping(value = "goods/query/{id}")
    public GoodsBean getUserById(@PathVariable(value = "id") Integer id){
        try {
            GoodsBean goodsBean = goodsService.getGoodsById(id);
            return goodsBean;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询
     * @return
     */
    @RequestMapping(value = "goods/query",method = RequestMethod.GET)
    public  ResponseEntity<JsonResult> getGoodsList(){
        JsonResult jsonResult = new JsonResult();
        try {
            List<GoodsBean> goodsBeanList = goodsService.getGoodsList();
            for (GoodsBean goodsBean:goodsBeanList
                 ) {
                int goods_id = goodsBean.getGoods_id();
                List<PicturesBean> list = pictureService.getPicturesByGoodsId(goods_id);
                if (list.isEmpty()){
                    goodsBean.setBanners(null);
                }else {
                    goodsBean.setBanners(list);
                }
            }
            JSONObject jsonObject = new JSONObject();
            //附加字段
            jsonObject.put("total",goodsBeanList.size());
            jsonObject.put("page_size",6);
            jsonResult.setJsonObject(jsonObject);
            //状态
            jsonResult.setCode("0");
            jsonResult.setStatus("OK");
            //数据
            jsonResult.setData(goodsBeanList);
            System.out.print(jsonResult);
            String s = "{'code':'0','total':'100','page_size':'10'}";
            return ResponseEntity.ok(jsonResult);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加
     * @param goodsBean
     * @return
     */
    @RequestMapping(value = "goods/add",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> add(@RequestBody GoodsBean goodsBean){
        JsonResult jsonResult = new JsonResult();
        try {
            int oderId = goodsService.add(goodsBean);
            if (oderId < 0){
                jsonResult.setData(oderId);
                jsonResult.setStatus("failed");
            }else {
                jsonResult.setData(oderId);
                jsonResult.setStatus("ok");
            }
        }catch (Exception e){
            jsonResult.setData(e.getClass().getName()+":"+e.getMessage());
            jsonResult.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping(value = "goods/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<JsonResult> delete(@PathVariable(value = "id") Integer id){
        JsonResult jsonResult = new JsonResult();
        try {
            int delId = goodsService.delete(id);
            if (delId < 0 ){
                jsonResult.setData(delId);
                jsonResult.setStatus("failed");
            }else {
                jsonResult.setData(delId);
                jsonResult.setStatus("ok");
            }
        }catch (Exception e){
            jsonResult.setData(e.getClass().getName()+":"+e.getMessage());
            jsonResult.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }

    @RequestMapping(value = "goods/queryBykey/{key}")
    public ResponseEntity<JsonResult> getGoodsListByKey(@PathVariable(value = "key") String key){
        JsonResult jsonResult = new JsonResult();
        try {
            List<GoodsBean> goodsBeanList = goodsService.getGoodsListByKey(key);
            if (!goodsBeanList.isEmpty()){
                for (GoodsBean goodsBean:goodsBeanList
                ) {
                    System.out.println(goodsBean.getGoods_text());
                    int goods_id = goodsBean.getGoods_id();
                    List<PicturesBean> list = pictureService.getPicturesByGoodsId(goods_id);
                    if (list.isEmpty()){
                        goodsBean.setBanners(null);
                    }else {
                        goodsBean.setBanners(list);
                    }
                }
                JSONObject jsonObject = new JSONObject();
                //附加字段
                jsonObject.put("total",goodsBeanList.size());
                jsonObject.put("page_size",6);
                jsonResult.setJsonObject(jsonObject);
                //状态
                jsonResult.setCode("0");
                jsonResult.setStatus("OK");
                //数据
                jsonResult.setData(goodsBeanList);
                System.out.print(jsonResult);
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }

}
