package org.example.model;

public interface UserRepository<T, I> {

    boolean create(T entity);

    T findById(I id);

    boolean update(T entity);

    boolean delete(T entity);
}