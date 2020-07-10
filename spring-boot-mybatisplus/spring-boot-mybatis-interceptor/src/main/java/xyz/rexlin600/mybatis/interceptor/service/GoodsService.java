package xyz.rexlin600.mybatis.interceptor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import xyz.rexlin600.mybatis.interceptor.entity.Goods;

/**
 * Goods service
 *
 * @author hekunlin
 */
public interface GoodsService extends IService<Goods> {

	/**
	 * Select list page info
	 *
	 * @param page page
	 * @param size size
	 * @return the page info
	 */
	PageInfo selectList(Integer page, Integer size);

	/**
	 * Select by id goods
	 *
	 * @param id id
	 * @return the goods
	 */
	Goods selectById(Long id);

}
