package com.luulsj.www.yuyue.server.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderCodeGenerator {
    // 使用单例模式，不允许直接创建实例
    private OrderCodeGenerator() {
    }

    // 创建一个空实例对象，类需要用的时候才赋值
    private static OrderCodeGenerator g = null;

    // 单例模式--懒汉模式
    public static synchronized OrderCodeGenerator getInstance() {
        if (g == null) {
            g = new OrderCodeGenerator();
        }
        return g;
    }

    // 全局自增数
    private static int count = 0;

    // 每毫秒秒最多生成多少订单（最好是像9999这种准备进位的值）
    private static final int total = 9999;

    // 格式化的时间字符串
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    // 获取当前时间年月日时分秒毫秒字符串
    private static String getNowDateStr() {
        return sdf.format(new Date());
    }

    // 记录上一次的时间，用来判断是否需要递增全局数
    private static String now = null;

    /*
     * 生成一个订单号
     * @param prefix 单个字符 C
     * @return 返回长度为22的字符串
     */
    public synchronized String getCode(String prefix) {
        String datastr = getNowDateStr();
        if (datastr.equals(now)) {
            count++;// 自增
        } else {
            count = 1;
            now = datastr;
        }
        int countInteger = String.valueOf(total).length() - String.valueOf(count).length();// 算补位
        String bu = "";// 补字符串
        for (int i = 0; i < countInteger; i++) {
            bu += "0";
        }
        bu += String.valueOf(count);
        if (count >= total) {
            count = 0;
        }
        return prefix + datastr + bu;
    }
}
