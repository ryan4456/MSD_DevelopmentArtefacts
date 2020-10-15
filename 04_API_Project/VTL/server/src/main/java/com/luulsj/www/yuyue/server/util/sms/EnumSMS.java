package com.luulsj.www.yuyue.server.util.sms;

public enum EnumSMS {

    SENDCODE(627391, "发送验证码"),
    FREE_SUCCESS(630981, "预约免费课程短信"),
    PAY_SUCCESS(630975, "报名课程支付成功");

    private int templateId;
    private String name;

    EnumSMS(int templateId, String name) {
        this.templateId = templateId;
        this.name = name;
    }

    public int getTemplateId() {
        return templateId;
    }
}
