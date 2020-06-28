package xyz.rexlin600.mybatis.interceptor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import xyz.rexlin600.mybatis.interceptor.entity.Goods;
import xyz.rexlin600.mybatis.interceptor.mapper.GoodsMapper;
import xyz.rexlin600.mybatis.interceptor.service.GoodsService;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rexlin600
 * @since 2020-05-09
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public PageInfo selectList(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Goods> list = goodsMapper.selectList(new LambdaQueryWrapper<>());
        PageInfo<Goods> goodsPageInfo = new PageInfo<>(list);
        return goodsPageInfo;
    }

    @Override
    public Goods selectById(Long id) {
        return goodsMapper.selectByGoodsId(id);
    }
    
}
