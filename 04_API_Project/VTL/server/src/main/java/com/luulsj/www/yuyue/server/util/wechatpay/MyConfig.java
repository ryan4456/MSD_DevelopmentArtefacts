package com.luulsj.www.yuyue.server.util.wechatpay;

import java.io.InputStream;

import com.jfinal.kit.PropKit;

public class MyConfig extends WXPayConfig{

	@Override
	String getAppID() {
		return PropKit.get("wechatpay.appid");
	}

	@Override
	String getMchID() {
		return PropKit.get("wechatpay.merchantid");
	}

	@Override
	String getKey() {
		return PropKit.get("wechatpay.partnerkey");
	}

	@Override
	InputStream getCertStream() {
		return null;
	}

	@Override
	IWXPayDomain getWXPayDomain() {
		return new IWXPayDomain() {

			public void report(String domain, long elapsedTimeMillis, Exception ex) {
				// TODO Auto-generated method stub
				
			}

			public DomainInfo getDomain(WXPayConfig config) {
				return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
			}
			
		};
	}

}
