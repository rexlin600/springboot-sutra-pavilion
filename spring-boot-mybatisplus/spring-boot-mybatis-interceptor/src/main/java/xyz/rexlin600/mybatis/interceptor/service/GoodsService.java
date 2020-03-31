package xyz.rexlin600.mybatis.interceptor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.rexlin600.mybatis.interceptor.entity.Goods;

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
     * @return
     */
    Goods selectById(Long id, Long ds);

}
