package com.mini.server.tools;

import java.util.HashMap;

public class MyMap extends HashMap<Object, Object> {
	private static final long serialVersionUID = 1L;

	public String get(String key) {
		return (String)super.get(key.toLowerCase());
	}
	public Object getO(String key) {
		return super.get(key.toLowerCase());
	}
	@Override
	public MyMap put(Object key, Object value) {
		super.put(key, value);
		return this;
	}
}
