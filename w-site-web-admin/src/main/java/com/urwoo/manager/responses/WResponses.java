package com.urwoo.manager.responses;

import java.util.HashMap;
import java.util.Map;

public class WResponses extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public WResponses() {
		put("code", 0);
	}
	
	public static WResponses error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static WResponses error(String msg) {
		return error(500, msg);
	}
	
	public static WResponses error(int code, String msg) {
		WResponses r = new WResponses();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static WResponses ok(String msg) {
		WResponses r = new WResponses();
		r.put("msg", msg);
		return r;
	}
	
	public static WResponses ok(Map<String, Object> map) {
		WResponses r = new WResponses();
		r.putAll(map);
		return r;
	}
	
	public static WResponses ok() {
		return new WResponses();
	}

	public WResponses put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
