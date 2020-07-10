package xyz.rexlin600.jdbc.service;


import xyz.rexlin600.jdbc.entity.User;

/**
 * User service
 *
 * @author hekunlin
 */
public interface UserService {

	/**
	 * Select by id user
	 *
	 * @param id id
	 * @return the user
	 */
	User selectById(Long id);

	/**
	 * Delete by id *
	 *
	 * @param id id
	 */
	void deleteById(Long id);

	/**
	 * Gets all users *
	 *
	 * @return the all users
	 */
	Long getAllUsers();

}