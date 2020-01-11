package xyz.rexlin600.elasticsearch.service;


import xyz.rexlin600.elasticsearch.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User findById(Long id);

    void deleteById(Long id);

    void delete(User user);

    List<User> findAll();

}