package com.sy.bishe.ygou.web;

import com.sy.bishe.ygou.bean.JsonResult;
import com.sy.bishe.ygou.bean.PicturesBean;
import com.sy.bishe.ygou.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PicturesController {

    @Autowired
    private PictureService pictureService;

    @GetMapping(value = "pictures/query/{id}")
    public PicturesBean getPicturesById(@PathVariable(value = "id") Integer id){
        try {
            PicturesBean picturesBean = pictureService.getPicturesById(id);
            return picturesBean;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping(value = "pictures/queryByGoodsId/{id}")
    public List<PicturesBean> getPicturesBloodsId(@PathVariable(value = "id") Integer id){
        try {
            List<PicturesBean> picturesBeanList = pictureService.getPicturesByGoodsId(id);
            return picturesBeanList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询
     * @return
     */
    @RequestMapping(value = "pictures/query",method = RequestMethod.GET)
    public List<PicturesBean> getPictureList(){
        try {
            List<PicturesBean> picturesBeans = pictureService.getPicturesList();
            return picturesBeans;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加
     * @param
     * @return
     */
    @RequestMapping(value = "pictures/add",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> add(@RequestBody PicturesBean picturesBean){
        JsonResult jsonResult = new JsonResult();
        try {
            int oderId = pictureService.add(picturesBean);
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
    @RequestMapping(value = "pictures/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<JsonResult> delete(@PathVariable(value = "id") Integer id){
        JsonResult jsonResult = new JsonResult();
        try {
            int delId = pictureService.delete(id);
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
}
