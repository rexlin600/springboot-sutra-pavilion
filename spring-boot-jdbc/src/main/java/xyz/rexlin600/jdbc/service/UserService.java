package xyz.rexlin600.jdbc.service;


import xyz.rexlin600.jdbc.entity.User;

/**
 * UserService 类
 *
 * @author: rexlin600
 * @since: 2020-01-10 22:11:04
 */
public interface UserService {

    /**
     * 根据id查找用户
     *
     * @param id
     * @return
     */
    User selectById(Long id);

    /**
     * 根据id删除一个用户
     *
     * @param id
     * @return
     */
    void deleteById(Long id);

    /**
     * 获取用户总数
     *
     * @return
     */
    Long getAllUsers();

}