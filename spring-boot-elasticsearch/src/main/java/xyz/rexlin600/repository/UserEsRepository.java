package xyz.rexlin600.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import xyz.rexlin600.model.User;

public interface UserEsRepository extends ElasticsearchRepository<User, Long> {

}
