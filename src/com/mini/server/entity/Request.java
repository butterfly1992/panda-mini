package com.mini.server.entity;


public class Request {
	private String imei;
	private String imsi;
	private String appid;
	private String headerkey;
	private String date;
	private String version;
	private String totalnumber;
	private String setupindex;
	
	
	public String getSetupindex() {
		return setupindex;
	}
	public void setSetupindex(String setupindex) {
		this.setupindex = setupindex;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getHeaderkey() {
		return headerkey;
	}
	public void setHeaderkey(String headerkey) {
		this.headerkey = headerkey;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTotalnumber() {
		return totalnumber;
	}
	public void setTotalnumber(String totalnumber) {
		this.totalnumber = totalnumber;
	}
	
}
