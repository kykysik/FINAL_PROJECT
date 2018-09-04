package model.dao;

import model.entity.Statistics;

import java.sql.Date;
import java.util.List;

public interface StatisticsDao extends  GenericDao<Statistics> {
    List<Statistics> find(Statistics statistics);
    int getNumberOfRows(int userId, Date date);
    List<Statistics> findAll(int currentPage, int recordsPerPage, int userId, Date date);
    void deleteAfterInsert(Date date);


    /*
     * Что нужно в статистике?
     * Запихнуть в статистику с food.
     * Менять там ничего нельзя.
     * Выбрать все, где текущий юзер = юзер с таблицы и текущая дата = дате с таблицы. !?
     *
     *
     *
     * */
}
