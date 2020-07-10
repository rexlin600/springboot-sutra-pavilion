package xyz.rexlin600.transaction.service;

import xyz.rexlin600.transaction.entity.User;

/**
 * User service
 *
 * @author hekunlin
 */
public interface UserService {

	/**
	 * Find by id user
	 *
	 * @param id id
	 * @return the user
	 */
	User findById(Long id);

	/**
	 * Add user *
	 *
	 * @param user user
	 */
	void addUser(User user);

}