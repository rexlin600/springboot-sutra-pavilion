package xyz.rexlin600.mybatis.interceptor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.util.StringUtils;
import xyz.rexlin600.mybatis.interceptor.annotation.InjectSql;
import xyz.rexlin600.mybatis.interceptor.entity.Goods;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author rexlin600
 * @since 2020-05-09
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    @InjectSql(flag = true)
    Goods selectByGoodsId(Long id);

    @InjectSql(flag = true)
    List<Goods> selectByGoodsId(String name);

}
