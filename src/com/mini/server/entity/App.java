package com.mini.server.entity;

public class App {

	private String id;
	private String userid;
	private String name;
	private String pck;
	private  String type;
	private String description;
	private String createtime;
	private String releasetime;
	private Integer status;
	private Integer firstpush;
	private Integer pushtime;//请求次数
	private Integer pushswitch;
	private String mname;//Mini市场名字
	private String micon;//logo
	private String mtheme;//背景色
	
	
	
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMicon() {
		return micon;
	}
	public void setMicon(String micon) {
		this.micon = micon;
	}
	public String getMtheme() {
		return mtheme;
	}
	public void setMtheme(String mtheme) {
		this.mtheme = mtheme;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPck() {
		return pck;
	}
	public void setPck(String pck) {
		this.pck = pck;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getReleasetime() {
		return releasetime;
	}
	public void setReleasetime(String releasetime) {
		this.releasetime = releasetime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getFirstpush() {
		return firstpush;
	}
	public void setFirstpush(Integer firstpush) {
		this.firstpush = firstpush;
	}
	public Integer getPushtime() {
		return pushtime;
	}
	public void setPushtime(Integer pushtime) {
		this.pushtime = pushtime;
	}
	public Integer getPushswitch() {
		return pushswitch;
	}
	public void setPushswitch(Integer pushswitch) {
		this.pushswitch = pushswitch;
	}
	
	
}
