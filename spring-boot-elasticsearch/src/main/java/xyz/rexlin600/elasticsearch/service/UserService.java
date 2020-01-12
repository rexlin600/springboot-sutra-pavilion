package xyz.rexlin600.elasticsearch.service;


import xyz.rexlin600.elasticsearch.model.User;

import java.util.List;

/**
 * @author rexlin600
 */
public interface UserService {

    /**
     * 创建
     *
     * @param user
     * @return
     */
    User create(User user);

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 根据ID删除
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     * 删除
     *
     * @param user
     */
    void delete(User user);

    /**
     * 查找列表
     *
     * @return
     */
    List<User> findAll();

}