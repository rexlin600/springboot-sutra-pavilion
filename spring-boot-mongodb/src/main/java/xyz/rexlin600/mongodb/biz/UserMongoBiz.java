package xyz.rexlin600.mongodb.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.rexlin600.mongodb.entity.User;
import xyz.rexlin600.mongodb.mongo.UserMongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * User mongo biz
 *
 * @author hekunlin
 */
@Service
public class UserMongoBiz {

	/**
	 * User mongo repository
	 */
	@Autowired
	private UserMongoRepository userMongoRepository;

	/**
	 * Save user *
	 *
	 * @param user user
	 */
	public void saveUser(User user) {
		userMongoRepository.save(user);
	}

	/**
	 * Gets by id *
	 *
	 * @param id id
	 * @return the by id
	 */
	public User getById(Long id) {
		Optional<User> optional = userMongoRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	/**
	 * Update *
	 *
	 * @param user user
	 */
	public void update(User user) {
		userMongoRepository.save(user);
	}

	/**
	 * Find by name list
	 *
	 * @param name name
	 * @return the list
	 */
	public List<User> findByName(String name) {
		List<User> list = userMongoRepository.findByName(name);
		return list;
	}

	/**
	 * Delete user by id *
	 *
	 * @param id id
	 */
	public void deleteUserById(Long id) {
		userMongoRepository.deleteById(id);
	}

	/**
	 * Gets user list *
	 *
	 * @return the user list
	 */
	public List<User> getUserList() {
		List<User> list = userMongoRepository.findAll();
		return list;
	}
}