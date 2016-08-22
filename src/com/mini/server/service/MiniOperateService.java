package com.mini.server.service;
import java.math.BigDecimal;

import com.mini.server.dao.DaoFactory;
import com.mini.server.dao.MiniOperateDao;
import com.mini.server.entity.Request;
import com.mini.server.tools.Utils;

public class MiniOperateService {
	private MiniOperateDao operateDao=DaoFactory.getInstance("operateDao", MiniOperateDao.class);
	
	public boolean Activeuser(String imei,String imsi,String appid,String version){//统计活跃用户
		if(operateDao.isthere("YQ_ACTIVEUSER", imei, imsi, version)==null){
			if(operateDao.insert("YQ_ACTIVEUSER", imei, imsi, appid,version)>0){
				Utils.log.info("活跃用户统计成功>>>>");
				return true;
			}else{
				Utils.log.info("活跃用户统计失败>>>>");
				return false;
			}
		}else{
			Utils.log.info("此活跃用户已登记");
			return false;
		}
	}
	public boolean Totalnumber(String version,String appid){//统计总访问量
		Request rs= operateDao.getTotal(version,appid);
		if(rs==null){
			if(operateDao.insertNumber(version,appid)>0){

				return true;
			}else{
				Utils.log.info("统计总访问量失败");
				return false;
			}
		}else{
				if(operateDao.updateTotal(version, appid)>0){
				Utils.log.info("统计总访问量更新成功");
				return true;
				}else{
					Utils.log.info("统计总访问量更新失败");
					return false;
				}
		}
		
	}
	public boolean Clickcount(String appid,String softid,String version){//统计点击量
		if(operateDao.getClickIndex("YQ_OPERATE", appid, softid)==null){
			if(operateDao.updateO("1", appid, softid, version)>0){//初次插入点击量
				Utils.log.info("初次插入点击量成功");
				return true;
			}else{
				Utils.log.info("初次插入点击量失败");
				return false;
			}
		}else{
				if(operateDao.updateoper("1", appid, softid, version)>0){//更新点击量+1
					Utils.log.info("点击量更新成功");
					return true;
				}else{
					Utils.log.info("点击量更新失败");
					return false;
				}
		}
	}
	public boolean Dlcount(String appid,String softid,String version){//统计下载完成量
		if(operateDao.getClickIndex("YQ_OPERATE", appid, softid)==null){
			if(operateDao.updateO("2", appid, softid, version)>0){//初次插入下载完成量
				Utils.log.info("初次插入下载完成量成功");
				return true;
			}else{
				Utils.log.info("初次插入下载完成量失败");
				return false;
			}
		}else{
				if(operateDao.updateoper("2", appid, softid, version)>0){//更新点击量+1
					Utils.log.info("下载完成量更新成功");
					return true;
				}else{
					Utils.log.info("下载完成量更新失败");
					return false;
				}
		}
	}
	public boolean Setcount(String appid,String softid ,String version){//统计安装量
		
		if(operateDao.getClickIndex("YQ_OPERATE", appid, softid)==null){
			if(operateDao.updateO("3", appid, softid, version)>0){//初次插入安装量
				Utils.log.info("初次插入安装量成功");
				return true;
			}else{
				Utils.log.info("初次插入安装量失败");
				return false;
			}
		}else{
				if(operateDao.updateoper("3", appid, softid, version)>0){//更新安装量+1
					Utils.log.info("安装量更新成功");
					return true;
				}else{
					Utils.log.info("安装量更新失败");
					return false;
				}
		}
	}
	public Request isthereSoft(String softid,String softindex){
		String sql="select id AS appid from DG_SOFT where id='"+softid+"' OR softindex='"+softindex+"'";
		return operateDao.find(sql);
	}
	public boolean softIndex(String imei,String imsi,String softindex){
		String tablename="DG_REQUEST_";
		if(!imsi.equals("")){//判断imsi为空时记录的表
			int carrieroperator=0;//运营商 0：所有 1：移动 2：联通 3：电信 4：铁通
			String mnc=imsi.substring(0,5);
			if("46000".equals(mnc) || "46002".equals(mnc) || "46007".equals(mnc)){
				//是移动用户
				carrieroperator=1;
				tablename+="YD_";
			}
			else if("46001".equals(mnc) || "46006".equals(mnc)){
				//是联通用户
				carrieroperator=2;
				tablename+="LT_";
			}
			else if("46003".equals(mnc) || "46005".equals(mnc)){
				//是电信用户
				carrieroperator=3;
				tablename+="DX_";
			}
			else if("46020".equals(mnc)){
				//是铁通用户
				carrieroperator=4;
				tablename+="TT_";
			}
			else{
				tablename+="QT_";
			}
			tablename+=new BigDecimal(Double.parseDouble(imsi.substring(imsi.length()-4))/20).setScale(0, BigDecimal.ROUND_HALF_UP);
		}else{
			tablename="YQ_NULLINDEX";
		}
		String sql="select setupindex from "+tablename+" where imei='"+imei+"' and imsi='"+imsi+"'";
		Request rs=null;
		rs=operateDao.find(sql);
		if(rs==null){
			Utils.log.info("执行插入");
			Utils.log.info("表名："+tablename+" imei："+imei+" imsi："+imsi);
			int l= operateDao.insertIndex(tablename, imei, imsi, softindex);
			if(l>0){
				Utils.log.info("插入成功");
				return true;
			}else{
				Utils.log.info("插入失败");
				return false;
			}
		}else{
			if(rs.getSetupindex().contains(",m"+softindex+",")){
				Utils.log.info("已经统计过！不重复统计");
				return false;
			}else{
				Utils.log.info("执行更新插入索引");
				Utils.log.info("表名："+tablename+" imei："+imei+" imsi："+imsi);
				if(operateDao.update(tablename, imei, imsi, softindex)>0){
					Utils.log.info("更新成功");
					return true;
				}else{
					Utils.log.info("更新失败");
					return false;
				}
			}
		}
	}
}
