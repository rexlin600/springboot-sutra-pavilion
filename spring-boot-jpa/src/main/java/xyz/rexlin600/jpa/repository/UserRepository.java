package xyz.rexlin600.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.rexlin600.jpa.entity.User;

/**
 * User JPA API
 *
 * @author: hekunlin
 * @date: 2020/1/8
 */
public interface UserRepository extends JpaRepository<User, Long> {


}