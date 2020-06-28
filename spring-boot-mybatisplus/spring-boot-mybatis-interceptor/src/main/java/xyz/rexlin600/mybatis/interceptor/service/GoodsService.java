package xyz.rexlin600.mybatis.interceptor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import xyz.rexlin600.mybatis.interceptor.entity.Goods;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author rexlin600
 * @since 2020-05-09
 */
public interface GoodsService extends IService<Goods> {

    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @return
     */
    PageInfo selectList(Integer page, Integer size);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    Goods selectById(Long id);

}
