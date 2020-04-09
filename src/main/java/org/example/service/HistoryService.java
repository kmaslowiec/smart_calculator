package org.example.service;

import org.example.entity.History;

import java.util.List;

public interface HistoryService {

    boolean add(History entry);

    List<History> readByDate(String date);
}
