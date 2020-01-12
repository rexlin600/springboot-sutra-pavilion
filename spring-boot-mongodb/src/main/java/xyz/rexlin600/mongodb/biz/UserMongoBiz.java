package xyz.rexlin600.mongodb.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.rexlin600.mongodb.entity.User;
import xyz.rexlin600.mongodb.mongo.UserMongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * UserMongoServiceImpl 实现类
 *
 * @author: rexlin600
 * @date: 2020-01-11
 */
@Service
public class UserMongoBiz {

    @Autowired
    private UserMongoRepository userMongoRepository;

    /**
     * 新增用户
     *
     * @param user
     */
    public void saveUser(User user) {
        User save = userMongoRepository.save(user);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public User getById(Long id) {
        Optional<User> optional = userMongoRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    /**
     * 更新用户
     *
     * @param user
     */
    public void update(User user) {
        userMongoRepository.save(user);
    }

    /**
     * 根据名称查询用户
     *
     * @param name
     * @return
     */
    public List<User> findByName(String name) {
        List<User> list = userMongoRepository.findByName(name);
        return list;
    }

    /**
     * 根据ID删除用户
     *
     * @param id
     */
    public void deleteUserById(Long id) {
        userMongoRepository.deleteById(id);
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    public List<User> getUserList() {
        List<User> list = userMongoRepository.findAll();
        return list;
    }
}