package com.mini.server.dao;

import com.mini.server.entity.Request;

public interface MiniOperateDao {
	
	public int insert(String tablename,String imei,String imsi,String appid,String version);
	public int update(String tablename,String imei,String imsi,String softindex);
	public int insertNumber(String version,String appid);
	public int installfromSD(String appid,String softindex,String sdkversion,String firstpush);
	public int statistics(String operate,String appid,String softid,String sdkversion,String firstpush);
	public void addData(String appid,String softid,String sdkversion,String firstpush);
	
	public Request isthere(String tablename,String imei,String imsi,String version);
	public Request getFscreenIndex(String tablename,String imei,String imsi);
	public Request getClickIndex(String tablename,String appid,String softid);
	public Request getDownloadIndex(String tablename,String imei,String imsi);
	public Request getTotal(String version,String appid);
	
	public int updateO(String operate,String appid,String softid,String sdkversion);
	public int updateoper(String oper,String appid,String softid,String version);
	public int updateTotal(String version,String appid);
	public Request find(String sql);
	public int insertIndex(String tablename,String imei,String imsi,String softindex);
}
