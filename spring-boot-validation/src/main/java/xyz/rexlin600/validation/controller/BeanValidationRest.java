package xyz.rexlin600.validation.controller;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.validation.common.apiparam.Response;
import xyz.rexlin600.validation.common.apiparam.ResponseGenerator;
import xyz.rexlin600.validation.param.*;


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


}

