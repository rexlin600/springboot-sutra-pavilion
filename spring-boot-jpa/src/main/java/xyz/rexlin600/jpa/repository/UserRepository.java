package xyz.rexlin600.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.rexlin600.jpa.entity.User;

import java.util.List;

/**
 * User JPA API
 *
 * @author: hekunlin
 * @since: 2020/1/8
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据名称查找
     *
     * @param userName
     * @return
     */
    List<User> findByUserName(String userName);

}