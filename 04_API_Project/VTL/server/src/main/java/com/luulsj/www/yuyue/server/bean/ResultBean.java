package com.luulsj.www.yuyue.server.bean;

public class ResultBean {

	private int code;
	private String message;
	private Object data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResultBean(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public ResultBean(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static void main(String[] args) {

	}

}
