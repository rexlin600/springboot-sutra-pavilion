package xyz.rexlin600.transaction.service;

import xyz.rexlin600.transaction.entity.User;

/**
 * UserService 类
 *
 * @author: rexlin600
 * @date: 2020-01-11
 */
public interface UserService {

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 新增
     * <p>
     * // @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
     *
     * @param user
     */
    void addUser(User user);

}