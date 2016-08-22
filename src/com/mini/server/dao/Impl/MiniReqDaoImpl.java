package com.mini.server.dao.Impl;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mini.server.dao.MiniReqDao;
import com.mini.server.entity.App;
import com.mini.server.entity.Request;
import com.mini.server.entity.Soft;
import com.mini.server.tools.JDBC;


public class MiniReqDaoImpl implements MiniReqDao{

	private QueryRunner qr=new QueryRunner(JDBC.getDataSource());
//	private Connection conn=JDBC.getConnection();
	
	@Override
	public App query(String appid) {
		// TODO Auto-generated method stub
//		Utils.log.info("------dao request query");
		App app=null;
		String sql="select status,pushswitch,firstpush,pushtime from DG_APP where id=?";
		try {
//			Utils.log.info("------query start");
			app=qr.query(sql, new BeanHandler<App>(App.class),appid);
//			Utils.log.info("------query end");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("e="+e);
		}
		return app;
	}
	
	@Override
	public List<Soft> list(){
		// TODO Auto-generated method stub
		List<Soft> list=null;
		try {
			String sql="SELECT COUNT(id) code  FROM DG_SOFT WHERE mstatus=1  ";//查询出在线的产品中数量
			Soft count=qr.query(sql, new BeanHandler<Soft>(Soft.class));
			if(count.getCode()>0){
				int max=count.getCode();
				int limit= (int) Math.round(Math.random()*(max-(max-5)))+(max-5);//随机获取在上线产品-5和上线产品之间的数量  
				sql="SELECT id,NAME,pck,LOGo,apk,softindex FROM DG_SOFT where mstatus=1 order by RAND() limit 0,"+limit;//随机排序获取不同数量的在线产品
				list=qr.query(sql, new BeanListHandler<Soft>(Soft.class));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Soft> getAdvers(String sql) {
		// TODO Auto-generated method stub
		List<Soft> list=null;
		try {
			list=qr.query(sql, new BeanListHandler<Soft>(Soft.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
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
	
	public App findApp(String sql) {
		// TODO Auto-generated method stub
		App u=null;
		try {
			u=qr.query(sql, new BeanHandler<App>(App.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public int countReq(String sql) {
		// TODO Auto-generated method stub
		int i=0;
		try {
			i=qr.update(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public boolean isthere() {
		// TODO Auto-generated method stub
		
		
		return false;
	}
	 
}
 
