package com.mini.server.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mini.server.service.MiniOperateService;
import com.mini.server.tools.Utils;

/**
 * Servlet implementation class DgOperateServlet
 */
public class total extends HttpServlet {
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
	 * 
	 * 操作量按照每天统计数据
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      http://192.168.1.126:8080/dgpush/dgpusho?imei=356812040911685&imsi=
	 *      460014155400403
	 *      &appid=8b3190a1eaae4db8a585d462bbeac144&sdkversion=1.0
	 *      &softid=11b65b2a-2257-47b1-b926-61da0b0d&softindex=2&oper=0
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 获取参数:
		// MemCachedClient mem=Memcached.getInstance();
		String version = request.getParameter("version");
		String appid = request.getParameter("appid");
		// Date expiryd=getDefinedDateTime(23,59,59,0);//当天的23:59:59:00
		MiniOperateService operservice = new MiniOperateService();
		if (version != null && appid != null) {// 进行统计当天请求次数
		// Utils.log.info("统计当天请求次数");
		// int reqSum=0;
		// if(mem.keyExists("totalKey")){
		// reqSum=(Integer) mem.get("totalKey");
		// }
		// mem.set("totalKey", reqSum+1,expiryd);
		// if(Utils.currentHours()%2==1&&Utils.currentMinutes()>=58&&Utils.currentMinutes()<=59){
			if (appid.equals("")) {
				appid = "8b3190a1eaae4db8a585d462bbeac144";
			}
			operservice.Totalnumber(version, appid);
			// }
		} else {
			Utils.log.info("参数不完整");
		}
	}

	public Date getDefinedDateTime(int hour, int minute, int second,
			int milliSecond) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.SECOND, minute);
		cal.set(Calendar.MINUTE, second);
		cal.set(Calendar.MILLISECOND, milliSecond);
		Date date = new Date(cal.getTimeInMillis());
		return date;
	}
}
