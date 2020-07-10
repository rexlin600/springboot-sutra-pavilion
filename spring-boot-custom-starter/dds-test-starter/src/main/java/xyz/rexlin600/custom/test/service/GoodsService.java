package xyz.rexlin600.custom.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import xyz.rexlin600.custom.test.entity.Goods;

/**
 * Goods service
 *
 * @author hekunlin
 */
public interface GoodsService extends IService<Goods> {

	/**
	 * Select by id goods
	 *
	 * @param id id
	 * @param ds ds
	 * @return the goods
	 */
	Goods selectById(@Param("id") Long id, @Param("ds") Long ds);

}
