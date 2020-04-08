package org.example.model;

import org.example.entity.History;

public interface HistoryRepository<T, I> {

    boolean create(History history);
}
