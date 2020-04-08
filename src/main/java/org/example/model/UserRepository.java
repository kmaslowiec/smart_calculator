package org.example.model;

public interface UserRepository<User, Long> {

    boolean create(User entity);

    User findById(Long id);

    boolean update(User entity);

    boolean delete(User entity);
}