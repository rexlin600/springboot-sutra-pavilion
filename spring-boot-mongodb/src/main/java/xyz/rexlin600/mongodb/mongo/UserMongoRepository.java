package xyz.rexlin600.mongodb.mongo;


import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.rexlin600.mongodb.entity.User;

import java.util.List;

/**
 * UserMongoService 类
 *
 * @author: rexlin600
 * @since: 2020-01-11
 */
public interface UserMongoRepository extends MongoRepository<User, Long> {

    /**
     * 建议使用 MongoRepository<T, TD> 来封装自己的 Mongo 操作类，这样在增加方法时 IDEA 会自动提示有哪些操作可以进行，并且不需要我们去实现！
     * <p>
     * 可以参考 images/mongo 的截图
     */

    /**
     * 根据ID删除
     *
     * @param id
     */
    @Override
    void deleteById(Long id);

    /**
     * 根据名称查找
     *
     * @param name
     * @return
     */
    List<User> findByName(String name);

}