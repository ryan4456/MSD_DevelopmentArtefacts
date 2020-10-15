package com.luulsj.www.yuyue.server.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static boolean checkPhone(String phone){
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][0-9]{10}$"); // 验证手机号
        m = p.matcher(phone);
        b = m.matches();
        return b;
    }

    public static String randomCode(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<length; i++){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }


    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }
}
