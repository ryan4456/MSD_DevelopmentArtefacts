package com.luulsj.www.yuyue.server.util.sms;

import java.util.List;

public interface ISms {

    /**
     * 根据模板发送短信
     * @param template 模板ID
     * @param phone 手机号
     * @param parameters 填充模板的参数
     * @return
     */
    public boolean sendSms(EnumSMS template, String phone, List<String> parameters);
}
