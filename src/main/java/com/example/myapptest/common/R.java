package com.example.myapptest.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2023/7/17 16:34
 * @Author HGA
 * @Class R
 * @Package com.example.myapp.common
 * Description:
 */

@Data
public class R<T> {

    private Integer code;
    private String msg;
    private T data;
    private Map map = new HashMap<>();

    public R(){

    }

    public R(int code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T>  R<T> success(T data){
        R<T> r = new R<T>();
        r.setCode(200);
        r.setData(data);
        return r;
    }

    public static <T>  R<T> error(String msg){
        R<T> r = new R<T>();
        r.setCode(400);
        r.setMsg(msg);
        return r;
    }

    public R<T> add(String key,Object value){
       this.map.put(key,value);
        return this;
    }

}
