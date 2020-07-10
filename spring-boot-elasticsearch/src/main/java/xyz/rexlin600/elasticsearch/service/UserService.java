package xyz.rexlin600.elasticsearch.service;


import xyz.rexlin600.elasticsearch.model.User;

import java.util.List;

/**
 * User service
 *
 * @author hekunlin
 */
public interface UserService {

	/**
	 * Create user
	 *
	 * @param user user
	 * @return the user
	 */
	User create(User user);

	/**
	 * Find by id user
	 *
	 * @param id id
	 * @return the user
	 */
	User findById(Long id);

	/**
	 * Delete by id *
	 *
	 * @param id id
	 */
	void deleteById(Long id);

	/**
	 * Delete *
	 *
	 * @param user user
	 */
	void delete(User user);

	/**
	 * Find all list
	 *
	 * @return the list
	 */
	List<User> findAll();

}