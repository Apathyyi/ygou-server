package com.sy.bishe.ygou.web;


import com.sy.bishe.ygou.bean.JsonResult;
import com.sy.bishe.ygou.bean.SortVerticalBean;
import com.sy.bishe.ygou.service.SortVerticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SortVerticalController {

    @Autowired
    private SortVerticalService sortVerticalService;

    @GetMapping(value = "sortvertical/query")
    public ResponseEntity<JsonResult> getSortVertical(){
        try {
            JsonResult jsonResult = new JsonResult();
            List<SortVerticalBean> list = sortVerticalService.getSortVertical();
            jsonResult.setCode("0");
            jsonResult.setStatus("ok");
            jsonResult.setData(list);
            return ResponseEntity.ok(jsonResult);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null ;
    }
}
