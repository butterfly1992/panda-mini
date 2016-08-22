package com.mini.server.entity;

public class Operate {

	private String appid;
	private String softid;
	private Integer lookcount;
	private Integer fscreencount;
	private Integer clickcount;
	private Integer downloadcount;
	private Integer setupcount;
	private String sdkversion;
	private String date;
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getSoftid() {
		return softid;
	}
	public void setSoftid(String softid) {
		this.softid = softid;
	}
	public Integer getLookcount() {
		return lookcount;
	}
	public void setLookcount(Integer lookcount) {
		this.lookcount = lookcount;
	}
	public Integer getFscreencount() {
		return fscreencount;
	}
	public void setFscreencount(Integer fscreencount) {
		this.fscreencount = fscreencount;
	}
	public Integer getClickcount() {
		return clickcount;
	}
	public void setClickcount(Integer clickcount) {
		this.clickcount = clickcount;
	}
	public Integer getDownloadcount() {
		return downloadcount;
	}
	public void setDownloadcount(Integer downloadcount) {
		this.downloadcount = downloadcount;
	}
	public Integer getSetupcount() {
		return setupcount;
	}
	public void setSetupcount(Integer setupcount) {
		this.setupcount = setupcount;
	}
	public String getSdkversion() {
		return sdkversion;
	}
	public void setSdkversion(String sdkversion) {
		this.sdkversion = sdkversion;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
