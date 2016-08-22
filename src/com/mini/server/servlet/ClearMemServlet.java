package com.mini.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mini.server.tools.MemDataManage;
import com.mini.server.tools.Utils;

/**
 * Servlet implementation class ClearMemServlet
 */
public class ClearMemServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static String result="";
	private static boolean ok=false;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClearMemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Utils.log.info("ClearMemServlet...");
		JSONObject json=new JSONObject();
		
		String type=request.getParameter("type");
		String imei=request.getParameter("imei");
		String imsi=request.getParameter("imsi");
		String memname=request.getParameter("mn");
		
		//  0--查看指定内存数据
		if(type!=null && "0".equals(type)){
			if(memname!=null){
				if("onlineindex".equals(memname)){
					memname="dg_online_softindex";
				}else if("reqcount".equals(memname)){
					memname="dg_request_count";
				}else if("firstpush".equals(memname)){
					memname="dg_firstpush";
				}else{
					memname="dg_"+memname+"_"+imei+imsi;
				}
				result=MemDataManage.accessSingledata(memname);
			}
			printJSON(response,result);
		}
		// 1--清空指定内存
		if(type!=null && "1".equals(type)){
			if(memname!=null){
				if("onlineindex".equals(memname)){
					memname="dg_online_softindex";
				}else if("reqcount".equals(memname)){//当日总请求次数(所有用户)
					memname="dg_request_count";
				}else if("firstpush".equals(memname)){
					memname="dg_firstpush";
				}else{
					memname="dg_"+memname+"_"+imei+imsi;
				}
				ok=MemDataManage.clearSingleData(memname);
				if(ok){
					printJSON(response,getJSON());
					return;
				}
			}
			printJSON(response,getErrorJSON());
			return;
		}
		// 2--查看指定用户所有内存数据
		if(type!=null && "2".equals(type)){
			if(imei!=null && imsi!=null){
				result=MemDataManage.accessAllmemdata(imei, imsi);
			}
			printJSON(response,result);
			return;
		}
		// 3--清空指定用户所有内存
		if(type!=null && "3".equals(type)){
			if(imei!=null && imsi!=null){
				boolean ok=MemDataManage.clearAllmemdata(imei, imsi);
				if(ok){
					json.put("flag", 1);
					printJSON(response,json.toString());
					return;
				}else{
					printJSON(response,getErrorJSON());
					return;
				}
			}
		}
		//4-清空服务器的Memcached数据
		/*if(type!=null && "4".equals(type)){
			boolean ok=true;
			
			if(ok){
				json.put("flag", 1);
				printJSON(response,json.toString());
				return;
			}else{
				printJSON(response,getErrorJSON());
				return;
			}
		}*/
		//查看指定用户的所有内存信息
		if(type!=null && "4".equals(type)){
			
		}
		//清空指定用户所有内存
        if(type!=null && "5".equals(type)){
			
		}
		
	}//doPost end
	
	private void printJSON(HttpServletResponse response, String jsonStr)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
	}
	private String getJSON() {
		JSONObject json = new JSONObject();
		json.put("flag", 1);
		return json.toString();
	}
	private String getErrorJSON() {
		JSONObject json = new JSONObject();
		json.put("flag", 0);
		return json.toString();
	}
}
