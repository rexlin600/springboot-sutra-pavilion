package xyz.rexlin600.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.rexlin600.transaction.entity.User;

/**
 * UserRepository 类
 *
 * @author: rexlin600
 * @date: 2020-01-11
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据名称查找
     *
     * @param name
     * @return
     */
    User findByName(String name);

    /**
     * 根据名称和年龄查找
     *
     * @param name
     * @param age
     * @return
     */
    User findByNameAndAge(String name, Integer age);

    /**
     * 根据名称查找
     *
     * @param name
     * @return
     */
    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);

}