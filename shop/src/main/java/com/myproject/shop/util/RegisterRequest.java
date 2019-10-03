package com.myproject.shop.util;

public class RegisterRequest {
	private String userid;
	private String username;
	private String userpw;
	private String checkuserPw;
	private String email;
	private String birth;
	private int phone;
	private String adr1;
	private String adr2;
	private String adr3;
	private String gender;

	public boolean isPwEqualToCheckPw() {
		return userpw.equals(checkuserPw);
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getCheckuserPw() {
		return checkuserPw;
	}
	public void setCheckuserPw(String checkuserPw) {
		this.checkuserPw = checkuserPw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getAdr1() {
		return adr1;
	}
	public void setAdr1(String adr1) {
		this.adr1 = adr1;
	}
	public String getAdr2() {
		return adr2;
	}
	public void setAdr2(String adr2) {
		this.adr2 = adr2;
	}
	public String getAdr3() {
		return adr3;
	}
	public void setAdr3(String adr3) {
		this.adr3 = adr3;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
