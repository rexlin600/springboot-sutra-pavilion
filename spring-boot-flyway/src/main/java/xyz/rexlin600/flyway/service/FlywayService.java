package xyz.rexlin600.mail.service;

import xyz.rexlin600.jdbc.entity.TbFlyway;

import java.util.List;

/**
 * FlywayService 接口
 *
 * @author: rexlin600
 * @date: 2020-01-11
 */
public interface FlywayService {

    void save(TbFlyway tbFlyway);

    TbFlyway findOne(Long id);

    Long findCount();

}