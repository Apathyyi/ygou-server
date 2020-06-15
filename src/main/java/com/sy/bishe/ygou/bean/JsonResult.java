package com.sy.bishe.ygou.bean;

import com.alibaba.fastjson.JSONObject;

/**
 * 返回json数据
 */
public class JsonResult {

    //状态码
    private String code = null;
    //状态
    private String status=null;
    //数据
    private Object data=null;
    //附加字段
    private JSONObject jsonObject = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "code='" + code + '\'' +
                ", status='" + status + '\'' +
                ", data=" + data +
                ", jsonObject=" + jsonObject +
                '}';
    }
}
