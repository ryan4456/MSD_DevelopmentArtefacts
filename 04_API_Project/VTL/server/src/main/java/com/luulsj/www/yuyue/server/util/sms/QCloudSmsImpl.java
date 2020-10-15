package com.luulsj.www.yuyue.server.util.sms;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QCloudSmsImpl implements ISms {

    // 短信应用 SDK AppID
    private static int appid = 123;
    // 短信应用 SDK AppKey
    private static String appkey = "123";
    // 签名, 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请
    String smsSign = "科界TechTrek";

    public boolean sendSms(EnumSMS template, String phone, List<String> parameters) {
        try {
            String[] params = parameters.toArray(new String[0]);

            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phone,
                    template.getTemplateId(), params, smsSign, "", "");
            System.out.println(result);
            return true;
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        ISms sender = new QCloudSmsImpl();
        List<String> list = new ArrayList<String>();
        //list.add("1234");
        boolean success = sender.sendSms(EnumSMS.PAY_SUCCESS, "17701304456", list);
        System.out.println(success);
    }
}
