package xyz.rexlin600.validation.controller;


import cn.hutool.core.bean.copier.BeanCopier;
import cn.hutool.core.bean.copier.CopyOptions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.validation.common.apiparam.Response;
import xyz.rexlin600.validation.common.apiparam.ResponseGenerator;
import xyz.rexlin600.validation.param.AddGoodsReq;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rexlin600
 * @since 2020-05-09
 */
@Validated
@RestController
@RequestMapping("/goods")
public class GoodsController {

    /**
     * 新增
     *
     * @param req
     * @return
     */
    @PostMapping("/save")
    public Response<Void> save(@RequestBody @Valid AddGoodsReq req) {

        // 后续逻辑 ...

        return ResponseGenerator.success();
    }


}

