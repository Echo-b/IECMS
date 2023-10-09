package cn.edu.cnu.iiot.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResponseData {
    private int code;
    private String msg;
    private String type;
    private Boolean success;
    private Map<String, Object> data = new HashMap<String, Object>();
//    private Object data;

    public static ResponseData success(String msg){
        ResponseData r = new ResponseData();
//        r.setData(null);
        r.setCode(ResultCode.SUCCESS);
        r.setSuccess(true);
        r.setType("success");
        r.setMsg(msg);
        return r;
    }
    public static ResponseData success(String msg, Map<String, Object> map) {
        ResponseData r = success(msg);
        r.setData(map);
        return r;
    }

    public static ResponseData warning(String msg) {
        ResponseData r = error(msg);
        r.setType("warning");
        return r;
    }

    public static ResponseData error(String msg) {
        ResponseData r = success(msg);
        r.setSuccess(false);
        r.setType("error");
        return r;
    }

    public static ResponseData fatal(String msg) {
        ResponseData r = error(msg);
        r.setCode(500);
        return r;
    }

    public ResponseData(int code, String msg, Map<String, Object> map) {
        this.code = code;
        this.msg = msg;
        this.data = map;
    }

    public ResponseData data(String key,Object value){
        this.data.put(key,value);
        return this;
    }

    public ResponseData data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

    private ResponseData(){}


}
