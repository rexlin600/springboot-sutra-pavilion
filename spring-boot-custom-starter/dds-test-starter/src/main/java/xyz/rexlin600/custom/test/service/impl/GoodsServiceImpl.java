package xyz.rexlin600.custom.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.rexlin600.custom.test.entity.Goods;
import xyz.rexlin600.custom.test.mapper.GoodsMapper;
import xyz.rexlin600.custom.test.service.GoodsService;
import xyz.rexlin600.starter.dds.util.DynamicDataSourceContextHolder;

/**
 * Goods service
 *
 * @author hekunlin
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Slf4j
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

	/**
	 * Goods mapper
	 */
	private final GoodsMapper goodsMapper;

	/**
	 * Goods service
	 *
	 * @param goodsMapper goods mapper
	 */
	@Autowired
	public GoodsServiceImpl(GoodsMapper goodsMapper) {
		this.goodsMapper = goodsMapper;
	}

	/**
	 * Select by id goods
	 *
	 * @param id id
	 * @param ds ds
	 * @return the goods
	 */
	@Override
	public Goods selectById(Long id, Long ds) {
		DynamicDataSourceContextHolder.push(ds);

		Goods goods = goodsMapper.selectById(id);
		return goods;
	}
}
