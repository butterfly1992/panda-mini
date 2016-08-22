package com.mini.server.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mini.server.service.MiniOperateService;
import com.mini.server.tools.Utils;

/**
 * Servlet implementation class DgOperateServlet
 */
public class miniOper extends HttpServlet {
	private static final long serialVersionUID = 1L;
 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return;
	}

	/**
	 * 
	 * 操作量按照每天统计数据
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * http://192.168.1.126:8080/dgpush/dgpusho?imei=356812040911685&imsi=460014155400403&appid=8b3190a1eaae4db8a585d462bbeac144&sdkversion=1.0&softid=11b65b2a-2257-47b1-b926-61da0b0d&softindex=2&oper=0
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取参数:
		String imsi=request.getParameter("imsi");
		String imei=request.getParameter("imei");
		String appid=request.getParameter("appid");
		String version=request.getParameter("version");
		
		MiniOperateService operservice=new MiniOperateService();
		String testid="test,8b3190a1eaae4db8a585d462bbeac144";
		if(testid.contains(appid)){
			Utils.log.info("测试ID不统计活跃用户");
		}else	if(version!=null&&imei!=null&&appid!=null){//统计活跃用户
				Utils.log.info("统计活跃用户");
				operservice.Activeuser(imei, imsi, appid,version);
			}else{
				Utils.log.info("参数不完整");
			}
		
	}
}
