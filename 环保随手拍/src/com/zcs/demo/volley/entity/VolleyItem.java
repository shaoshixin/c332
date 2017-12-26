package com.zcs.demo.volley.entity;

public class VolleyItem {
	private long id;
	private String name;
	private String imgUrl;
	private String user;
	private String time;
	private String count;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String name) {
		this.user = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}

	public void setTime(String name) {
		this.time = name;
	}
	public String getCount() {
		return count;
	}

	public void setCount(String name) {
		this.count = name;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl= imgUrl;
	}

	public String getImgUrl() {
		// TODO Auto-generated method stub
		return imgUrl;
	}

}
