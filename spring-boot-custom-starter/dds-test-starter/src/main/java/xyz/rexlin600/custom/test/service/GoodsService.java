package xyz.rexlin600.custom.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import xyz.rexlin600.custom.test.entity.Goods;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author rexlin600
 * @since 2020-03-16
 */
public interface GoodsService extends IService<Goods> {

    /**
     * 根据ID查询
     *
     * @param id
     * @param ds
     * @return
     */
    Goods selectById(@Param("id") Long id, @Param("ds") Long ds);

}
