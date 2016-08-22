package com.mini.server.service;

import java.util.List;

import com.mini.server.dao.DaoFactory;
import com.mini.server.dao.MiniReqDao;
import com.mini.server.entity.App;
import com.mini.server.entity.Soft;

public class MiniReqService {
	private MiniReqDao reqdao=DaoFactory.getInstance("reqdao", MiniReqDao.class);
	/*
	 * 查询产品，并且随机返回不同的数量
	 */
	public List<Soft>  returnSoft(String appid,String imei){
		return reqdao.list();
	}
	/**
	 * 判断此开关打开的这个应用是否存在
	 * @param appid
	 * @return
	 */
	public App isthereApp(String appid){
		String sql = "select id appid,mname,mlogo micon,mtheme  from DG_APP where id='"+appid+"'  AND listswitch=1";
		return  reqdao.findApp(sql);
	}
	/**
	 * 检测sdk的版本
	 * @return
	 */
	public String checkVersion(){
		String sql="select version from DG_SDKVERSION where id='2'";
		return reqdao.find(sql).getVersion();
	}
	/**
	 * 或许宣传标题
	 * @return
	 */
	public String gettitle() {
		// TODO Auto-generated method stub
		String sql="SELECT ltitle totalnumber FROM YQ_LTITLE WHERE id=1";
		return reqdao.find(sql).getTotalnumber();
	}
	
}
