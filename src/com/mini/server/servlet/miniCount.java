package com.mini.server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.mini.server.entity.Request;
import com.mini.server.service.MiniOperateService;
import com.mini.server.tools.Utils;

/**
 * Servlet implementation class InstallBaseServlet
 */
public class miniCount extends HttpServlet {
	private static final long serialVersionUID = 1L;

 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) 
	 *      http://192.168.1.126:8080/dgpush/dgpushi?imei=356812040911685
	 *      &imsi=460014155400403
	 *      &appid=8b3190a1eaae4db8a585d462bbeac144&sdkversion=1.1
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 获取参数：
		String appid = request.getParameter("appid");// 游戏ID
		String version = request.getParameter("version");// SDK版本
		String softid = request.getParameter("softid");
		String softindex = request.getParameter("softindex");
		String imei = request.getParameter("imei");
		String imsi = request.getParameter("imsi");
		String operate = request.getParameter("operate");
		MiniOperateService operservice = new MiniOperateService();
		Utils.log.info("执行 miniCount servlet 统计点击，下载，安装量");
		String testid="test,8b3190a1eaae4db8a585d462bbeac144,ffe05e21bd044b5181212858751a8724";
		if(testid.contains(appid)){
			Utils.log.info("测试ID不统计点击，下载量");
			if (operate != null && version != null && appid != null
					&& softid != null && softindex != null
					&& operate.equals("2") && imsi != null) {// 统计安装量
				Utils.log.info("统计测试id安装量");
				if (operservice.softIndex(imei, imsi, softindex)) {
					operservice.Setcount(appid, softid, version);
				} else {
					Utils.log.info("安装索引已存在！");
				}
			} else {
				Utils.log.info("测试id统计操作==" + operate);
			}
		} else if (operate != null && version != null && appid != null
				&& softid != null && softindex != null && imsi != null) {
			Request req = operservice.isthereSoft(softid, softindex);
			if (req != null) {
				if (softid.equals("")) {
					softid = req.getAppid();
				}
				if (operate.equals("0")) {// 统计点击量
					Utils.log.info("统计点击量");
					operservice.Clickcount(appid, softid, version);
				} else if (operate.equals("1")) {// 统计下载完成量
					Utils.log.info("统计下载完成量");
					operservice.Dlcount(appid, softid, version);
				} else if (operate.equals("2")) {// 统计安装量
					Utils.log.info("统计安装量");
					if (operservice.softIndex(imei, imsi, softindex)) {
						operservice.Setcount(appid, softid, version);
					} else {
						Utils.log.info("安装索引已存在！");
					}
				} else {
					Utils.log.info("未统计");
				}
			}
		} else {
			Utils.log.info("参数不完整");
		}
	}
}
