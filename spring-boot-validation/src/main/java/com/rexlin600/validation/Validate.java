package com.rexlin600.validation;

import org.springframework.util.StringUtils;

/**
 * @author: hekunlin
 * @date: 2020/6/2
 */
public class Validate {

    public static final int EIGHT = 8;

    /**
     * 注意进行校验的方法要写成静态方法，否则会出现 TypeError: xxx is not a function 的错误
     *
     * @param name
     * @param age
     * @param classes
     * @return
     */
    public static boolean checkParams(String name, int age, String classes) {
        if (StringUtils.isEmpty(name)) {
            return false;
        }

        if (age > EIGHT && classes != null) {
            return true;
        } else {
            return false;
        }
    }

}