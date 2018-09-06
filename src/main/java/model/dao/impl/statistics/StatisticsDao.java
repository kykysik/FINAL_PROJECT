package model.dao.impl.statistics;

import model.dao.GenericDao;
import model.entity.Statistics;

import java.sql.Date;
import java.util.List;

public interface StatisticsDao extends GenericDao<Statistics> {
    List<Statistics> find(Statistics statistics);
    int getNumberOfRows(int userId, Date date);
    List<Statistics> findAll(int currentPage, int recordsPerPage, int userId, Date date);
    void deleteAfterInsert(Date date);
}
