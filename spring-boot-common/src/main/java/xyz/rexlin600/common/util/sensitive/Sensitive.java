package xyz.rexlin600.common.util.sensitive;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对象脱敏注解
 * <p>
 * 原理：数据只有在输出的时候进行脱敏处理即可，其它都是在内存阶段，相对来说都是比较安全的，输出阶段我想到了json序列化
 * <p>
 * 参考文章：
 * http://www.scienjus.com/get-field-annotation-property-by-jackson-contextualserializer/
 * http://www.voidcn.com/article/p-ezjgnfeh-bee.html
 *
 * @author hekunlin
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveSerialize.class)
public @interface Sensitive {

    /**
     * 脱敏数据类型, 非Customer时, 将忽略 refixNoMaskLen 和 suffixNoMaskLen 和 maskStr
     *
     * @return {@link SensitiveTypeEnum}
     */
    SensitiveTypeEnum type() default SensitiveTypeEnum.CUSTOMER;

    /**
     * 前置不需要打码的长度
     *
     * @return {@link Integer}
     */
    int prefixNoMaskLen() default 0;

    /**
     * 后置不需要打码的长度
     *
     * @return {@link Integer}
     */
    int suffixNoMaskLen() default 0;

    /**
     * 用什么打码
     *
     * @return {@link String}
     */
    String maskStr() default "*";

}
