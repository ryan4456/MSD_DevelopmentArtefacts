package com.luulsj.www.yuyue.server.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;
import com.luulsj.www.yuyue.server.controller.*;
import com.luulsj.www.yuyue.server.handler.WebSocketHandler;
import com.luulsj.www.yuyue.server.model.*;

public class YuyueConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants me) {
		me.setEncoding("utf-8");
		loadPropertyFile("config.properties");
		me.setViewType(ViewType.JSP);
		me.setViewExtension(".jsp");
		me.setDevMode(true);
		me.setError404View("/common/404.jsp");
	}

	@Override
	public void configEngine(Engine arg0) {
	}

	@Override
	public void configHandler(Handlers me) {
		me.add(new WebSocketHandler("^/websocket.ws"));
	}

	@Override
	public void configInterceptor(Interceptors me) {
		//me.add(new AuthInterceptor());
	}

	@Override
	public void configPlugin(Plugins me) {
//		DruidPlugin druidPlugin = new DruidPlugin(getProperty("jdbc.url"),
//				getProperty("jdbc.username").trim(), getProperty("jdbc.password").trim(),
//				getProperty("jdbc.driver").trim());
//		me.add(druidPlugin);
		
//		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
//		me.add(arp);
//		// table
//		arp.addMapping("wechat_user", WechatUser.class);

		RedisPlugin redisPlugin = new RedisPlugin("token", getProperty("redis.host"), getPropertyToInt("redis.port"), getPropertyToInt("redis.timeout"), getProperty("redis.password"));
		me.add(redisPlugin);
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/login", LoginController.class);
	}

}
