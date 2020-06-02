package com.rexlin600.validation;

/**
 * @author: hekunlin
 * @date: 2020/6/2
 */
public class Validate {

    /**
     * 注意进行校验的方法要写成静态方法，否则会出现 TypeError: xxx is not a function 的错误
     *
     * @param name
     * @param age
     * @param classes
     * @return
     */
    public static boolean checkParams(String name, int age, String classes) {
        if (name != null && age > 8 & classes != null) {
            return true;
        } else {
            return false;
        }
    }

}