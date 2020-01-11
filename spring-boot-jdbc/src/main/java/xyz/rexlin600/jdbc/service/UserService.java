package xyz.rexlin600.elasticsearch.service;

import xyz.rexlin600.docker.entity.User;

/**
 * UserService 类
 *
 * @author: rexlin600
 * @date: 2020-01-10 22:11:04
 */
public interface UserService {

    /**
     * 根据id查找用户
     *
     * @param id
     */
    User selectById(Long id);

    /**
     * 根据id删除一个用户
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     * 获取用户总数
     */
    Long getAllUsers();

}