package com.urwoo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class WResult implements BaseBizCode {
    /**
     * 响应业务状态
     */
    private Integer code;
    /**
     * 响应消息
     */
    private String msg;
    /**
     * 响应中的数据
     */
    private Object data;

    public WResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public WResult(Object data) {
        this.code = SUCCESS;
        this.msg = "OK";
        this.data = data;
    }

    public static WResult ok(Object data) {
        return new WResult(data);
    }

    public static WResult ok() {
        return new WResult(null);
    }

    public static WResult count(Long count){
        Map<String, Object> pages = new HashMap<>();
        pages.put("count", count);
        return ok(pages);
    }

    public static <T> WResult page(List<T> list, Long count, Long start, Integer limit){
        Map<String, Object> pages = new HashMap<>();
        pages.put("list", list);
        pages.put("count", count);
        pages.put("start", start);
        pages.put("limit", limit);
        return ok(pages);
    }

    public static WResult build(Integer code) {
        return new WResult(code, "error", null);
    }

    public static WResult build(Integer code, String msg) {
        return new WResult(code, msg, null);
    }

    public static WResult build(Integer code, String msg, Object data) {
        return new WResult(code, msg, data);
    }

    public Boolean isOK() {
        return this.code == SUCCESS;
    }
}
