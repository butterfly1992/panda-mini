package com.mini.server.dao.Impl;

import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.mini.server.dao.MiniOperateDao;
import com.mini.server.entity.Request;
import com.mini.server.tools.JDBC;
import com.mini.server.tools.Utils;

public class MiniOperateDaoImpl implements MiniOperateDao{

	private QueryRunner qr=new QueryRunner(JDBC.getDataSource());
	/**
	 * 查询是否存在
	 **/
	@Override
	public Request isthere(String tablename,String imei,String imsi,String version) {
		// TODO Auto-generated method stub
		Request  request=null;
		String sql="select version from "+tablename+" where imei=? and imsi=? and version=? and date=?";
		try {
			request=qr.query(sql, new BeanHandler<Request>(Request.class),imei,imsi,version,Utils.DateTime());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return request;
	}
	/**
	 * 查询单一用户的全屏展示索引
	 **/
	@Override
	public Request getFscreenIndex(String tablename,String imei,String imsi) {
		// TODO Auto-generated method stub
		Request ur=null;
		String sql="select fscreenindex,date from "+tablename+" where imei=? and imsi=?";
		try {
			ur=qr.query(sql, new BeanHandler<Request>(Request.class),imei,imsi);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ur;
	}
	/**
	 * 查询单一用户的点击下载索引
	 **/
	@Override
	public Request getClickIndex(String tablename, String appid, String softid) {
		// TODO Auto-generated method stub
		Request ur=null;
		String sql="select date from "+tablename+" where appid=? and softid=? and date=?";
		try {
			ur=qr.query(sql, new BeanHandler<Request>(Request.class),appid,softid,Utils.DateTime());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ur;
	}
	/**
	 * 查询单一用户的下载成功索引
	 **/
	@Override
	public Request getDownloadIndex(String tablename, String imei,
			String imsi) {
		// TODO Auto-generated method stub
		Request ur=null;
		String sql="select downloadindex,date from "+tablename+" where imei=? and imsi=?";
		try {
			ur=qr.query(sql, new BeanHandler<Request>(Request.class),imei,imsi);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ur;
	}
	/**
	 * 查询单一用户的安装成功索引
	 **/
	@Override
	public Request getTotal(String version,String appid) {
		// TODO Auto-generated method stub
		Request ur=null;
		String sql="select date from YQ_OPERATE where appid=? and date=? and version=? and softid='123'";
		try {
			ur=qr.query(sql, new BeanHandler<Request>(Request.class),appid,Utils.DateTime(),version);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ur;
	}
	/**
	 *
	 **/
	@Override
	public int insert(String tablename,String imei,String imsi,String appid,String version) {
		// TODO Auto-generated method stub
		int l=0;
		String sql="insert into "+tablename+"(imei,imsi,appid,date,version) VALUES(?,?,?,?,?)";
		try {
			l=qr.update(sql,imei,imsi, appid,Utils.DateTime(),version);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	/**
	 *更新单一用户的请求记录 
	 **/
	@Override
	public int update(String tablename,String imei,String imsi,String softindex) {
		// TODO Auto-generated method stub
		int l=0;
		String sql="update "+tablename +" set setupindex=(CASE WHEN setupindex='' THEN ',m"+softindex+",' ELSE concat(setupindex,'m"+softindex+",') END) where imei=? and imsi=?";
		try {
			l=qr.update(sql,imei,imsi);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	/**
	 *统计各种量 
	 **/
/*	@Override
	public int statistics(String operate, String appid, String softid,String sdkversion) {
		// TODO Auto-generated method stub
		int l=0;
		String currentTime=Utils.DateTime();
		String sql="update DG_OPERATE set ";
		if("0".equals(operate)){
			sql+="lookcount=lookcount+1 ";
		}else if("1".equals(operate)){
			sql+="fscreencount=fscreencount+1 ";
		}else if("2".equals(operate)){
			sql+="clickcount=clickcount+1 ";
		}else if("3".equals(operate)){
			sql+="downloadcount=downloadcount+1 ";
		}else if("4".equals(operate)){
			sql+="setupcount=setupcount+1 ";
		}else{
			return 0;
		}
		
		sql+="where appid=? and softid=? and sdkversion=? and date=?";
		try {
			l=qr.update(sql,appid,softid,sdkversion,currentTime);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}*/
	public int installfromSD(String appid,String softindex,String sdkversion,String firstpush){
		int l=0;
		String currentTime=Utils.DateTime();
		String sql="update DG_OPERATE set setupcount=setupcount+1 "+
		                 "where appid=? and softid=(select id from DG_SOFT where status=1 and softindex=?) "+
				         "and sdkversion=? and date=? and firstpush=?";
		try {
			l=qr.update(sql,appid,softindex,sdkversion,currentTime,firstpush);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	/**
	 *更新DG_OPERATE表数据 
	 **/
	@Override
	public int statistics(String operate, String appid, String softid,
			String sdkversion, String firstpush) {
		// TODO Auto-generated method stub
		int l=0;
		String currentTime=Utils.DateTime();
		String sql="update DG_OPERATE set ";
		if("0".equals(operate)){
			sql+="lookcount=lookcount+1 ";
		}else if("1".equals(operate)){
			sql+="fscreencount=fscreencount+1 ";
		}else if("2".equals(operate)){
			sql+="clickcount=clickcount+1 ";
		}else if("3".equals(operate)){
			sql+="downloadcount=downloadcount+1 ";
		}else if("4".equals(operate)){
			sql+="setupcount=setupcount+1 ";
		}else{
			return 0;
		}
		sql+="where appid=? and softid=? and sdkversion=? and date=? and firstpush=?";
		try {
			l=qr.update(sql,appid,softid,sdkversion,currentTime,firstpush);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	/**
	 *插入DG_OPERATE表数据
	 **/
	@Override
	public void addData(String appid, String softid,String sdkversion,String firstpush) {
		// TODO Auto-generated method stub
		String currentTime=Utils.DateTime();
		String sql="insert into DG_OPERATE(appid,softid,lookcount,fscreencount,clickcount,downloadcount,setupcount,sdkversion,date,firstpush) VALUES(?,?,?,?,?,?,?,?,?,?)";
		try {
			qr.update(sql,appid,softid,1,0,0,0,0,sdkversion,currentTime,firstpush);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public int updateO(String operate, String appid, String softid,
			String version) {
		// TODO Auto-generated method stub
		int l=0;
		String currentTime=Utils.DateTime();
		String sql="insert into YQ_OPERATE (appid,softid,clickcount,dlcount,setcount,version,date) values(?,?,?,?,?,?,?)";
		
		try{
			if("1".equals(operate)){//点击
				l=qr.update(sql,appid,softid,1,0,0,version,currentTime);
			}else if("2".equals(operate)){//下载完成
				l=qr.update(sql,appid,softid,0,1,0,version,currentTime);
			}else if("3".equals(operate)){//安装
				l=qr.update(sql,appid,softid,0,0,1,version,currentTime);
			}else{
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		Utils.log.info("操作表记录信息======="+l);
		return l;
	}
	@Override
	public int insertNumber(String version,String appid) {
		// TODO Auto-generated method stub
		int l=0;
		String currentTime=Utils.DateTime();
		String sql="insert into YQ_OPERATE (date,appid,softid,lookcount,version,setcount) VALUES(?,?,?,?,?,?)";
		try {
			l=qr.update(sql,currentTime,appid,"123",1,version,0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Utils.log.info("统计总访问量数据库更新"+l);
		return l;
	}
	public int updateoper(String oper,String appid,String softid,String version){
		int l=0;
		String currentTime=Utils.DateTime();
		String sql="update YQ_OPERATE set ";
		if("1".equals(oper)){
			sql+="clickcount=clickcount+1 ";
		}else if("2".equals(oper)){
			sql+="dlcount=dlcount+1 ";
		}else if("3".equals(oper)){
			sql+="setcount=setcount+1 ";
		}else{
			return 0;
		}
		sql+="where appid=? and softid=? and version=? and date=?";
		try {
			l=qr.update(sql,appid,softid,version,currentTime);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	@Override
	public int updateTotal(String version,String  appid) {
		// TODO Auto-generated method stub
		int l=0;
		String currentTime=Utils.DateTime();
		String sql="update YQ_OPERATE set lookcount=lookcount+1 where softid='123' and appid=? and date=? and version=?";
		try {
			l=qr.update(sql,appid,currentTime,version);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	@Override
	public Request find(String sql) {
		// TODO Auto-generated method stub
		Request u=null;
		try {
			u=qr.query(sql, new BeanHandler<Request>(Request.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	@Override
	public int insertIndex(String tablename, String imei, String imsi,
			String softindex) {
		// TODO Auto-generated method stub
		int l=0;
		String sql="insert into "+tablename+"(imei,imsi,setupindex,date) VALUES(?,?,?,?)";
		try {
			l=qr.update(sql,imei,imsi, ",m"+softindex+",",Utils.DateTime());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
}
