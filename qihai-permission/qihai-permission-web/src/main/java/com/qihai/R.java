package com.qihai;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 * 
 * @author zhugj
 * @email zhuguojin@qihaiyun.com
 * @date 2017-06-08 0:00
 */
public class R extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	public R() {
		put("code", 0);
		put("msg", "success");
	}

	public static R error() {
		return error(500, "鏈煡寮傚父锛岃鑱旂郴绠＄悊鍛�");
	}

	public static R error(String msg) {
		return error(500, msg);
	}

	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}

	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}

	public static R ok() {
		return new R();
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public Object getData() {
		return this.get("data");
	}

	public static R setData(Object data) {
		R r = new R();
		r.put("data", data);
		return r;
	}
}
