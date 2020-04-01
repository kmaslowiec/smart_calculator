package org.example.model;

public interface SqlRepository<T, I> {

    boolean create(T entity);

    T findById(I id);

    boolean update(T entity);

    boolean delete(T entity);
}