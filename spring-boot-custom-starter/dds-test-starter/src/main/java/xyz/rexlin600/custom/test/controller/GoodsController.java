package xyz.rexlin600.custom.test.controller;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.extension.api.ApiController;
import xyz.rexlin600.custom.test.entity.Goods;
import xyz.rexlin600.custom.test.service.GoodsService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rexlin600
 * @since 2020-03-16
 */
@RestController
@RequestMapping("/goods")
@AllArgsConstructor
public class GoodsController extends ApiController {

    private final GoodsService goodsService;

    /**
     * 根据ID查询测试数据
     *
     * @param id
     * @param ds
     * @return
     */
    @GetMapping("{id}")
    public R get(@PathVariable("id") Long id,
                 @RequestParam(value = "ds", required = false) Long ds) {
        Goods goods = goodsService.selectById(id, ds);
        return R.ok(goods);
    }

}

