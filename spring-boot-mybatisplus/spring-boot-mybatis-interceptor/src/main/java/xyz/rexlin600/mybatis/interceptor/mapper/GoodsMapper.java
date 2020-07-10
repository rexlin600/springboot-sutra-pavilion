package xyz.rexlin600.mybatis.interceptor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.rexlin600.mybatis.interceptor.annotation.InjectSql;
import xyz.rexlin600.mybatis.interceptor.entity.Goods;

/**
 * Goods mapper
 *
 * @author hekunlin
 */
public interface GoodsMapper extends BaseMapper<Goods> {

	/**
	 * Select by goods id goods
	 *
	 * @param id id
	 * @return the goods
	 */
	@InjectSql(flag = true)
	Goods selectByGoodsId(Long id);

}
