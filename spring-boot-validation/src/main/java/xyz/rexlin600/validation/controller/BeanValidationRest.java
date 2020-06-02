package xyz.rexlin600.validation.controller;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.management.Agent;
import xyz.rexlin600.validation.common.apiparam.Response;
import xyz.rexlin600.validation.common.apiparam.ResponseGenerator;
import xyz.rexlin600.validation.param.*;
import xyz.rexlin600.validation.param.group.*;

import javax.validation.groups.Default;
import java.util.Base64;

/**
 * <p>
 * 参数校验前端控制器
 * </p>
 *
 * @author rexlin600
 * @menu 参数校验
 * @since 2020-05-09
 */
@RestController
@RequestMapping("/bean/validation")
public class BeanValidationRest {

    /**
     * 检查空
     *
     * @param req
     * @return
     */
    @PostMapping("/checkNull")
    public Response<Void> checkNull(@RequestBody @Validated NullReq req) {

        // 后续逻辑 ...

        return ResponseGenerator.success();
    }


    /**
     * 检查 Boolean
     *
     * @param req
     * @return
     */
    @PostMapping("/checkBoolean")
    public Response<Void> CheckBoolean(@RequestBody @Validated BooleanReq req) {

        // 后续逻辑 ...

        return ResponseGenerator.success();
    }


    /**
     * 数值检查
     *
     * @param req
     * @return
     */
    @PostMapping("/checkNumberReq")
    public Response<Void> CheckNumberReq(@RequestBody @Validated NumberReq req) {

        // 后续逻辑 ...

        return ResponseGenerator.success();
    }


    /**
     * 长度检查
     *
     * @param req
     * @return
     */
    @PostMapping("/checkLengthReq")
    public Response<Void> CheckLengthReq(@RequestBody @Validated LengthReq req) {

        // 后续逻辑 ...

        return ResponseGenerator.success();
    }


    /**
     * Range 检查
     *
     * @param req
     * @return
     */
    @PostMapping("/checkRangeReq")
    public Response<Void> CheckRangeReq(@RequestBody @Validated RangeReq req) {

        // 后续逻辑 ...

        return ResponseGenerator.success();
    }


    /**
     * 其他类型检查
     *
     * @param req
     * @return
     */
    @PostMapping("/checkOtherReq")
    public Response<Void> CheckOtherReq(@RequestBody @Validated OtherReq req) {

        // 后续逻辑 ...

        return ResponseGenerator.success();
    }


    /**
     * 自定义类型检查
     *
     * @param req
     * @return
     */
    @PostMapping("/checkCustomReq")
    public Response<Void> CheckCustomReq(@RequestBody @Validated CustomReq req) {

        // 后续逻辑 ...

        return ResponseGenerator.success();
    }

    // -----------------------------------------------------------------------------------------------
    // 分组校验
    // -----------------------------------------------------------------------------------------------

    /**
     * 分组校验-Name
     *
     * @param req
     * @return
     */
    @PostMapping("/group1")
    public Response<Void> group1(@RequestBody @Validated(Name.class) GroupReq req) {

        // 本示例会校验 name、list 字段（Name.class 继承了 Default）

        // 后续逻辑 ...

        return ResponseGenerator.success();
    }

    /**
     * 分组校验-Clasess
     *
     * @param req
     * @return
     */
    @PostMapping("/group2")
    public Response<Void> group2(@RequestBody @Validated(Classes.class) GroupReq req) {

        // 本示例会校验 clasess、list 字段（Classes.class 继承了 Default）

        // 后续逻辑 ...

        return ResponseGenerator.success();
    }

    /**
     * 分组校验-Age
     *
     * @param req
     * @return
     */
    @PostMapping("/group3")
    public Response<Void> group3(@RequestBody @Validated(Age.class) GroupReq req) {

        // 本示例只会校验 age 字段；因为 Age.class 没有继承 Default，因此不会校验 list 字段！！！（Age.class 没有继承了 Default）

        // 后续逻辑 ...

        return ResponseGenerator.success();
    }

    /**
     * 分组校验-Default
     *
     * @param req
     * @return
     */
    @PostMapping("/group4")
    public Response<Void> group4(@RequestBody @Validated({Default.class}) GroupReq req) {

        // 本示例只会校验 list 字段

        // 后续逻辑 ...

        return ResponseGenerator.success();
    }

    /**
     * 分组校验-All Group
     *
     * @param req
     * @return
     */
    @PostMapping("/group5")
    public Response<Void> group5(@RequestBody @Validated({Name.class, Classes.class, Age.class}) GroupReq req) {

        // 本示例会校验 name、clasess、age、list 字段

        // 后续逻辑 ...

        return ResponseGenerator.success();
    }

    // -----------------------------------------------------------------------------------------------
    // 复杂校验
    // -----------------------------------------------------------------------------------------------

    /**
     * 复杂校验-Script
     *
     * @param req
     * @return
     */
    @PostMapping("/script")
    public Response<Void> script(@RequestBody @Validated ScriptReq req) {

        // 后续逻辑 ...

        return ResponseGenerator.success();
    }

}

