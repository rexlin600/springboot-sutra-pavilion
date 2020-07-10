package xyz.rexlin600.elasticsearch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.rexlin600.elasticsearch.model.User;
import xyz.rexlin600.elasticsearch.repository.UserEsRepository;
import xyz.rexlin600.elasticsearch.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * User service
 *
 * @author hekunlin
 */
@Service
public class UserServiceImpl implements UserService {

	/**
	 * User es repository
	 */
	@Autowired
	private UserEsRepository userEsRepository;

	/**
	 * Create user
	 *
	 * @param user user
	 * @return the user
	 */
	@Override
	public User create(User user) {
		if (null == user) {
			return null;
		}
		System.out.println(userEsRepository);
		return userEsRepository.save(user);
	}

	/**
	 * Find by id user
	 *
	 * @param id id
	 * @return the user
	 */
	@Override
	public User findById(Long id) {
		if (null == id) {
			return null;
		}
		Optional<User> optional = userEsRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	/**
	 * Find all list
	 *
	 * @return the list
	 */
	@Override
	public List<User> findAll() {
		final List<User> users = new ArrayList<User>();
		userEsRepository.findAll().forEach(item -> {
			users.add(item);
		});
		return users;
	}

	/**
	 * Delete by id *
	 *
	 * @param id id
	 */
	@Override
	public void deleteById(Long id) {
		userEsRepository.deleteById(id);
	}

	/**
	 * Delete *
	 *
	 * @param user user
	 */
	@Override
	public void delete(User user) {
		userEsRepository.delete(user);
	}
}
