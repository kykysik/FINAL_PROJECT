package model.services.statistics;

import model.dao.impl.statistics.StatisticsDao;
import model.dao.impl.statistics.StatisticsDaoFactory;
import model.entity.Statistics;

import java.sql.Date;
import java.util.List;

public class StatisticsService {

    StatisticsDaoFactory daoFactory = StatisticsDaoFactory.getInstance();

    public List<Statistics> findAll(int currentPage, int recordsPerPage, int userId, Date date) {
        try (StatisticsDao dao = daoFactory.findAll()) {
            return dao.findAll(currentPage, recordsPerPage, userId, date);
        }
    }

    public int getNumberOfRows(int userId, Date date) {
        try (StatisticsDao dao = daoFactory.getNumberOfRows()) {
            return dao.getNumberOfRows(userId, date);
        }
    }

    public void deleteAfterInsert(Date date) {
        try (StatisticsDao dao = daoFactory.deleteAfterInsert()) {
             dao.deleteAfterInsert(date);
        }
    }

}
