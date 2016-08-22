package com.mini.server.dao;

import java.util.List;

import com.mini.server.entity.App;
import com.mini.server.entity.Request;
import com.mini.server.entity.Soft;

public interface MiniReqDao {

	public App query(String appid);
	public List<Soft> list();
	public List<Soft> getAdvers(String sql);
	public Request find(String sql);
	public App findApp(String sql);
	public int countReq(String sql);
	public boolean isthere();
	
}
