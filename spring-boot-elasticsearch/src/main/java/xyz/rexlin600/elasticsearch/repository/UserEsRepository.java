package xyz.rexlin600.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import xyz.rexlin600.elasticsearch.model.User;

/**
 * @author rexlin600
 */
public interface UserEsRepository extends ElasticsearchRepository<User, Long> {

}
