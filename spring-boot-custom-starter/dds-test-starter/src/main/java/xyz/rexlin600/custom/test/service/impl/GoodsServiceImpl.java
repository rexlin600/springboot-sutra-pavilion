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
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rexlin600
 * @since 2020-03-16
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Slf4j
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    private final GoodsMapper goodsMapper;

    @Autowired
    public GoodsServiceImpl(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    @Override
    public Goods selectById(Long id, Long ds) {
        DynamicDataSourceContextHolder.push(ds);

        Goods goods = goodsMapper.selectById(id);
        return goods;
    }
}
