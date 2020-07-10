package xyz.rexlin600.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.rexlin600.jpa.entity.User;

import java.util.List;

/**
 * User repository
 *
 * @author hekunlin
 */
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Find by user name list
	 *
	 * @param userName user name
	 * @return the list
	 */
	List<User> findByUserName(String userName);

}