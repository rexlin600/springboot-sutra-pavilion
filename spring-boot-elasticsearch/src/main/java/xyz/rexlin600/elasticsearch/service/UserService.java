package xyz.rexlin600.elasticsearch.service;


import xyz.rexlin600.elasticsearch.model.User;

import java.util.List;

/**
 * 用户服务
 *
 * @author rexlin600
 */
public interface UserService {

    /**
     * 创建
     *
     * @param user 用户
     * @return {@link xyz.rexlin600.elasticsearch.model.User}
     */
    User create(User user);

    /**
     * 根据ID查找
     *
     * @param id ID
     * @return {@link xyz.rexlin600.elasticsearch.model.User}
     */
    User findById(Long id);

    /**
     * 根据ID删除
     *
     * @param id ID
     */
    void deleteById(Long id);

    /**
     * 删除
     *
     * @param user 用户
     */
    void delete(User user);

    /**
     * 查找列表
     *
     * @return {@link List}
     */
    List<User> findAll();

}