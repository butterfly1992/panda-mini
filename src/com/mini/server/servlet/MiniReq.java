package com.mini.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mini.server.entity.App;
import com.mini.server.entity.Soft;
import com.mini.server.service.MiniReqService;
import com.mini.server.tools.Utils;
import com.mysql.jdbc.Util;

/**
 * Servlet implementation class DgRequestServlet
 **/
public class MiniReq extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		// return ;
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Utils.log.info("《《《《请求类型？》》》》》：" + request.getMethod()
				+ ".........................................");
		String appid = request.getParameter("appid");
		String imei = request.getParameter("imei");
		String testid = "test,8b3190a1eaae4db8a585d462bbeac144,ffe05e21bd044b5181212858751a8724";
		if (testid.contains(appid)) {
			MiniReqService service = new MiniReqService();
			Utils.log.info("测试id请求 ");
			List<Soft> list = service.returnSoft(appid, imei);
			App app = new App();
			app.setMname("应用推荐");
			app.setMicon("http://www.duoguopush.com/dg/dgpush/icon/mini1.png");
			app.setMtheme("#f72138");
			JSONObject json = new JSONObject(app);
			String title = service.gettitle();
			json.put("version", service.checkVersion());
			json.put("flag", 1);
			json.put("result", list);
			json.put("title", title);
			printJSON(response, json.toString());
		} else if (appid != null && imei != null) {
			Utils.log.info("appid:" + appid + "；imei:" + imei);
			MiniReqService service = new MiniReqService();
			App app = service.isthereApp(appid);
			if (app == null) {
				Utils.log.info("没有此款APP 或APP开关未开");
				getErrorJSON();
			} else {
				List<Soft> list = service.returnSoft(appid, imei);
				Utils.log.info("『result:  len：" + list.size() + ";imei:"
						+ imei + "；appid：" + appid + "』");
				JSONObject json = new JSONObject(app);
				String title = service.gettitle();
				json.put("version", service.checkVersion());
				json.put("flag", 1);
				json.put("result", list);
				json.put("title", title);
				printJSON(response, json.toString());
			}
		} else {
			getErrorJSON();
		}
	}

	private void printJSON(HttpServletResponse response, String jsonStr)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
	}

	private String getErrorJSON() {
		JSONObject json = new JSONObject();
		json.put("flag", 0);
		return json.toString();
	}
}
