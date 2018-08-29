package model.dao;

import model.entity.Statistics;

import java.util.List;

public interface StatisticsDao extends  GenericDao<Statistics> {
    List<Statistics> find(Statistics statistics);

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
