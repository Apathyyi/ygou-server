package com.sy.bishe.ygou.web;

import com.sy.bishe.ygou.bean.AddressBean;
import com.sy.bishe.ygou.bean.JsonResult;
import com.sy.bishe.ygou.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "address/query/{id}")
    public ResponseEntity<JsonResult> getAddressList(@PathVariable(value = "id") Integer id){
        JsonResult jsonResult = new JsonResult();
        try {
            List<AddressBean> list =  addressService.getAddressList(id);
            if (!list.isEmpty()){
                jsonResult.setStatus("ok");
                jsonResult.setData(list);
            }else {
                jsonResult.setStatus("error");
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }
    @RequestMapping(value = "address/delete/{id}")
    public ResponseEntity<JsonResult> deleteAddressById(@PathVariable(value = "id") Integer id){
        JsonResult jsonResult = new JsonResult();
        try {
            int result = addressService.deleteAddressById(id);
            if (result!=0){
                jsonResult.setStatus("ok");
            }else {
                jsonResult.setStatus("error");
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }
    @RequestMapping(value = "address/add",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> addAddressById(@RequestBody AddressBean addressBean) {
        System.out.println("Address" + addressBean.getAddress_name());
        System.out.println("Address" + addressBean.toString());
        JsonResult jsonResult = new JsonResult();
        try {
            int result = addressService.add(addressBean);
            if (result!=0){
                jsonResult.setStatus("ok");
            }else {
                jsonResult.setStatus("error");
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }
}
