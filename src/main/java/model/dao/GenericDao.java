package model.dao;

import model.entity.User;

import java.util.List;

public interface GenericDao<T> extends AutoCloseable {

    void create (T entity);
    List<T> findAll();
    void update(T entity);
    void delete(T entity);
    void close();
  /*  List<T> findAll(int currentPage, int recordsPerPage) ;// Вывести список food
    int getNumberOfRows();*/
}
