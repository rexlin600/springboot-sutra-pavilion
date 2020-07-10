package xyz.rexlin600.mongodb.mongo;


import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.rexlin600.mongodb.entity.User;

import java.util.List;

/**
 * User mongo repository
 *
 * @author hekunlin
 */
public interface UserMongoRepository extends MongoRepository<User, Long> {

	/**
	 * <p>建议使用 MongoRepository来封装自己的 Mongo 操作类，
	 * 这样在增加方法时 IDEA 会自动提示有哪些操作可以进行，并且不需要我们去实现！
	 * 可以参考 images目录下mongo 的截图</p>
	 *
	 * @param id id
	 */
	@Override
	void deleteById(Long id);

	/**
	 * Find by name list
	 *
	 * @param name name
	 * @return the list
	 */
	List<User> findByName(String name);

}