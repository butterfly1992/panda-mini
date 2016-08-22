package com.mini.server.tools;

import org.json.JSONObject;

import com.danga.MemCached.MemCachedClient;
import com.mini.server.tools.Memcached;

public class MemDataManage {

	private static MemCachedClient mem=Memcached.getInstance();
	private static JSONObject json=null;
	/**
	 * 查看指定用户所有内存数据
	 **/
	public static String accessAllmemdata(String imei,String imsi){
		json=new JSONObject();
		if(!"".equals(imei) && !"".equals(imsi)){
			Utils.log.info("获取内存数据");
			
			Utils.log.info("dg_online_softindex:"+mem.get("dg_online_softindex"));
			Utils.log.info("dg_userpush_:"+mem.get("dg_userpush_"+imei+imsi));
			Utils.log.info("dg_ispush_:"+mem.get("dg_ispush_"+imei+imsi));
			Utils.log.info("dg_reqnum_:"+mem.get("dg_reqnum_"+imei+imsi));
			Utils.log.info("dg_firstpush:"+mem.get("dg_firstpush"+imei+imsi));
			Utils.log.info("dg_request_count:"+mem.get("dg_request_count"));
			
			json.put("flag", 1);
			json.put("所有线上产品", mem.get("dg_online_softindex"));
			json.put("当日总请求次数", mem.get("dg_request_count"));
			json.put("用户未展示索引", mem.get("dg_userpush_"+imei+imsi));
			json.put("当次是否展示", mem.get("dg_ispush_"+imei+imsi));
			json.put("触发器请求次数", mem.get("dg_reqnum_"+imei+imsi));
			json.put("FirstPush请求次数", mem.get("dg_firstpush"+imei+imsi));
		}else{
			json.put("flag", 0);
		}
		return json.toString();

	}
	/**
	 * 清除指定用户所有内存数据
	 **/
	public static boolean clearAllmemdata(String imei,String imsi){
		if(!"".equals(imei) && !"".equals(imsi)){
			Utils.log.info("清除内存数据");
			Utils.log.info("dg_online_softindex:"+mem.delete("dg_online_softindex"));
			Utils.log.info("dg_userpush_:"+mem.delete("dg_userpush_"+imei+imsi));
			Utils.log.info("dg_ispush_:"+mem.delete("dg_ispush_"+imei+imsi));
			Utils.log.info("dg_reqnum_:"+mem.delete("dg_reqnum_"+imei+imsi));
			Utils.log.info("dg_firstpush:"+mem.delete("dg_firstpush"+imei+imsi));
			Utils.log.info("不清除dg_request_count:"+mem.get("dg_request_count"));
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 查看单个内存数据
	 **/
	public static String accessSingledata(String memname){
		json=new JSONObject();
		if(!"".equals(memname)){
			Utils.log.info(memname+":"+mem.get(memname));
			json.put("flag", 1);
			json.put(memname, mem.get(memname));
		}else{
			json.put("flag", 0);
		}
		return json.toString();
	}
	/**
	 * 清除单个内存数据
	 **/
	public static boolean clearSingleData(String memname){
		if(!"".equals(memname)){
			Utils.log.info(memname+":"+mem.get(memname));
			return mem.delete(memname);
		}else{
			return false;
		}
	}
	/**
	 * 清空服务器Memcached所有数据 
	 **/
	public static boolean flushAllMemData(){
		int reqnum=0;
		if(mem.keyExists("dg_request_count")){
			reqnum=(Integer) mem.get("dg_request_count");
		}
		mem.flushAll();
		
		mem.set("dg_request_count", reqnum);
		return true;
	}
	/**
	 * 查询特定用户所有的内存数据 
	 **/
	public static String singleAllMemDate(String imei,String imsi){
		json=new JSONObject();
		return "";
	}
	
}
