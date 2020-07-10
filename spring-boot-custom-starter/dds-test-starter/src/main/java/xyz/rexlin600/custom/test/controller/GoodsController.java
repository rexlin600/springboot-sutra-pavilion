package xyz.rexlin600.custom.test.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.custom.test.entity.Goods;
import xyz.rexlin600.custom.test.service.GoodsService;

/**
 * Goods controller
 *
 * @author hekunlin
 */
@RestController
@RequestMapping("/goods")
@AllArgsConstructor
public class GoodsController extends ApiController {

	/**
	 * Goods service
	 */
	private final GoodsService goodsService;

	/**
	 * Get r
	 *
	 * @param id id
	 * @param ds ds
	 * @return the r
	 */
	@GetMapping("{id}")
	public R get(@PathVariable("id") Long id,
				 @RequestParam(value = "ds", required = false) Long ds) {
		Goods goods = goodsService.selectById(id, ds);
		return R.ok(goods);
	}

}

