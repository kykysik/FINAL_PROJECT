package model.dao;

import model.entity.User;

import java.util.List;

public interface GenericDao<T> extends AutoCloseable {
    boolean create (T entity);
    void update(T entity);
    void delete(int id);
    void close();
  /*  List<T> findAll(int currentPage, int recordsPerPage) ;// Вывести список food
    int getNumberOfRows();*/
}
