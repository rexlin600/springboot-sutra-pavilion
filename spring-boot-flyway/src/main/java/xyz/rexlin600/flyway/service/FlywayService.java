package xyz.rexlin600.flyway.service;


import xyz.rexlin600.flyway.entity.TbFlyway;

/**
 * FlywayService 接口
 *
 * @author: rexlin600
 * @since: 2020-01-11
 */
public interface FlywayService {

    /**
     * 保存
     *
     * @param tbFlyway
     */
    void save(TbFlyway tbFlyway);

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     */
    TbFlyway findOne(Long id);

    /**
     * 统计总数
     *
     * @return
     */
    Long findCount();

}