package xyz.rexlin600.validation.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ScriptAssert;

import java.io.Serializable;

/**
 * 脚本校验 请求类
 *
 * @author rexlin600
 * @ScriptAssert(lang = "javascript", script = "xyz.rexlin600.validation.param.ScriptReq.checkParams(_this.name,_this.age,_this.classes)", message = "参数错误：复杂校验失败")
 * <p>
 * 注意：
 * 1. 测试发现这里的 @ScriptAssert 中的 script 参数指定的包名必须为 com.xxx；目前没有找到原因，底层是调用的 native 方法
 * 2. script 支持
 * @ScriptAssert(script = "xyz.rexlin600.validation.param.ScriptReq.checkParams(_this.name,_this.age,_this.classes)", lang = "JavaScript", message = "参数错误：复杂校验失败")
 * @author: hekunlin
 * @since: 2020/6/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ScriptAssert(lang = "JavaScript", script = "com.rexlin600.validation.Validate.checkParams(_this.name,_this.age,_this.classes)", message = "参数错误：复杂校验失败")
public class ScriptReq implements Serializable {

    /**
     * 姓名
     */
    private String name;

    /**
     * s
     * 年龄
     */
    private int age;

    /**
     * 班级
     */
    private String classes;

}