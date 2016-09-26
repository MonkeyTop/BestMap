package com.example.zyb.bestmap.util;

/**
 * 闰年月算法
 * <p>
 * Created by zyb on 2016/9/20.
 */
public class NumberHelper {
    public static String LeftPad_Tow_Zero(int str) {
        java.text.DecimalFormat format = new java.text.DecimalFormat("00");
        return format.format(str);
    }
}
