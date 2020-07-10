package xyz.rexlin600.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.rexlin600.transaction.entity.User;

/**
 * User repository
 *
 * @author hekunlin
 */
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Find by name user
	 *
	 * @param name name
	 * @return the user
	 */
	User findByName(String name);

	/**
	 * Find by name and age user
	 *
	 * @param name name
	 * @param age  age
	 * @return the user
	 */
	User findByNameAndAge(String name, Integer age);

	/**
	 * Find user user
	 *
	 * @param name name
	 * @return the user
	 */
	@Query("from User u where u.name=:name")
	User findUser(@Param("name") String name);

}