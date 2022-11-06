package com.pang.kinect_project.utils;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class CheckInputUtils {

    /**
     * 校验手机号
     * @param phone
     * @return
     */
    public static boolean verifyPhone(String phone) {
        String regex = "^[1]([3-9])[0-9]{9}$";
        boolean isMatch = false;
        if (StringUtils.isEmpty(phone)) {
            System.out.println("手机号不能为空");
        } else if (phone.length() != 11) {
            System.out.println("手机号应为11位数");
        } else {
            isMatch = Pattern.matches(regex, phone);
        }
        return isMatch;
    }
}
