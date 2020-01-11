package xyz.rexlin600.swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.rexlin600.docker.entity.User;

/**
 * User JPA API
 *
 * @author: hekunlin
 * @date: 2020/1/8
 */
public interface UserRepository extends JpaRepository<User, Long> {


}