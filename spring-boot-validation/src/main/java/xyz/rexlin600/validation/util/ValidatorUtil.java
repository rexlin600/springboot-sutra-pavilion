package xyz.rexlin600.validation.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验工具类
 *
 * @author: hekunlin
 * @date: 2020/6/1
 */
public class ValidatorUtil {

    /**
     * 用户昵称、密码筛选规则
     */
    public static final String NICKNAME_CONSTRAINT_REGEXP = "^[\\u4e00-\\u9fa5_a-zA-Z0-9-]{1,20}$";
    public static final String USER_PASSWORD_CONSTRAINT_REGEXP = "^.{8,16}$";

    /**
     * 手机号、手机号脱敏筛选正则
     */
    public static final String PHONE_CONSTRAINT_REGEXP = "^(1)\\d{10}$";
    public static final String PHONE_BLUR_REGEX = "(\\d{3})\\d{4}(\\d{4})";

    /**
     * 身份证号筛选规则
     */
    public static final String ID_CARD_CONSTRAINT_REGEXP = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";

    /**
     * 校验是否为手机号
     *
     * @param value
     * @return
     */
    public static boolean isPhone(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }

        Pattern pattern = Pattern.compile(PHONE_CONSTRAINT_REGEXP);
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }


}