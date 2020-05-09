package xyz.rexlin600.mybatis.interceptor.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import xyz.rexlin600.mybatis.interceptor.service.GoodsService;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rexlin600
 * @since 2020-05-09
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    private final GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping("/page")
    public R goods(@RequestParam(value = "page") @NotNull(message = "分页参数，页码不可为空") Integer page,
                   @RequestParam(value = "size") @NotNull(message = "分页参数，每页条数不可为空") Integer size) {
        PageInfo pageInfo = goodsService.selectList(page, size);
        return R.ok(pageInfo);
    }

    @GetMapping("/{id}")
    public R get(@PathVariable(value = "id") Long id) {
        return R.ok(this.goodsService.selectById(id));
    }

}

