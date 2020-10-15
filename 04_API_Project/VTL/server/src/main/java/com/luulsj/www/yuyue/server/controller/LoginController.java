package com.luulsj.www.yuyue.server.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import com.jfinal.plugin.redis.RedisPlugin;
import com.luulsj.www.yuyue.server.bean.ResultBean;
import com.luulsj.www.yuyue.server.util.StringUtil;


public class LoginController extends Controller{

	private static final String EMAIL = "test";
	private static final String PASSWORD = "test";

	public void login(){
		ResultBean resultBean = new ResultBean(1, "Invalid email or password");
		String email = getPara("email");
		String password = getPara("password");
		if(EMAIL.equals(email) && PASSWORD.equals(password)){
			resultBean.setCode(0);
			resultBean.setMessage("Login success");
			String randomUUID = StringUtil.randomUUID();
			resultBean.setData(randomUUID);
			renderJson();
		}
		renderJson(resultBean);
	}

	public static void main(String[] args) {
		RedisPlugin rp = new RedisPlugin("userCache", "120.27.20.59", 6379, "5f745f8224ae9a7b214d8ad9efe2edef");
        rp.start();

        Cache userCache = Redis.use("userCache");
        userCache.setex("phone", 20, "123455");
        System.out.println(userCache);
	}
}
