package com.sy.bishe.ygou.web;


import com.sy.bishe.ygou.bean.BrandBean;
import com.sy.bishe.ygou.bean.JsonResult;
import com.sy.bishe.ygou.bean.SortContentBean;
import com.sy.bishe.ygou.service.BrandService;
import com.sy.bishe.ygou.service.SortContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class SortContentController {

    @Autowired
    private SortContentService sortContentService;

    @Autowired
    private BrandService brandService;

    @GetMapping(value = "sortcontent/query/{id}")
    public ResponseEntity<JsonResult> getSortContent(@PathVariable(value = "id") Integer id){
        JsonResult jsonResult = new JsonResult();
        try {
            jsonResult.setCode("0");
            jsonResult.setStatus("ok");
            List<SortContentBean> list = sortContentService.getSortContentById(id);
            for (SortContentBean sortContentBean:list){
                int sortid = sortContentBean.getContent_id();
                //通过id 获取品牌
                List<BrandBean> brandBeans = brandService.getBrandById(sortid);
                sortContentBean.setBrandBean(brandBeans);
            }
            jsonResult.setData(list);
            return ResponseEntity.ok(jsonResult);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping(value = "sortcontent/queryByName/{brand}")
    public ResponseEntity<JsonResult> getBrandIdByName(@PathVariable(value = "brand") String brand){
        JsonResult jsonResult = new JsonResult();
        int id = sortContentService.getBrandIdByName(brand);
        if (id==0){
            jsonResult.setStatus("null");
        }else {
            jsonResult.setStatus("ok");
            jsonResult.setData(id);
        }

        return  ResponseEntity.ok(jsonResult);
    }

}
