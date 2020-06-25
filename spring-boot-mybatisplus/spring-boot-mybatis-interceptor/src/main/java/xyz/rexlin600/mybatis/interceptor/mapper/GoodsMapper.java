package xyz.rexlin600.mybatis.interceptor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.rexlin600.mybatis.interceptor.annotation.InjectSql;
import xyz.rexlin600.mybatis.interceptor.entity.Goods;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author rexlin600
 * @since 2020-05-09
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @InjectSql(flag = true)
    Goods selectByGoodsId(Long id);

}
