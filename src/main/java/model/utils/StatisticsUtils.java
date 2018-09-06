package model.utils;

import model.dao.impl.statistics.StatisticsDao;
import model.dao.mapper.statistics.StatisticsMapImpl;
import model.dao.mapper.statistics.StatisticsMapper;
import model.entity.Statistics;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static view.SqlConstant.*;

public class StatisticsUtils implements StatisticsDao {

    private static final String ID_ALL = "idAll";
    StatisticsMapper statisticsMapper = new StatisticsMapImpl();
    private static final Logger logger = Logger.getLogger(StatisticsUtils.class);

    Connection connection;
    public StatisticsUtils(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Statistics stat) {
        return false;
    }

    @Override
    public void deleteAfterInsert(Date date) {
        try {

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            try(PreparedStatement pstm = connection.prepareStatement(INSERT_SELECT_STATISTICS);
            PreparedStatement pstm1 = connection.prepareStatement(DELETE_AFTER_INSERT_PRODUCTS);
                PreparedStatement pstm2 = connection.prepareStatement(DELETE_AFTER_INSERT_PORTIONS)) {

                pstm.setDate(1,date);
                pstm.setDate(2,date);

                pstm1.setDate(1,date);
                pstm2.setDate(1,date);

                pstm.executeUpdate();
                pstm1.executeUpdate();
                pstm2.executeUpdate();
                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage());
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public List<Statistics> find(Statistics statistics) { // Вывести список food
        return null;
    }

    @Override
    public List<Statistics> findAll(int currentPage, int recordsPerPage, int userId, Date date) {
        int start = currentPage * recordsPerPage - recordsPerPage;
        List<Statistics> foods = new ArrayList<>();
        Map<Integer, Statistics> statisticsHashMap = new HashMap<>();

        try(PreparedStatement pstm = connection.prepareStatement(FIND_ALL_STATISTICS)) {
            pstm.setInt(1, userId);
            pstm.setDate(2, date);
            pstm.setInt(3, start);
            pstm.setInt(4, recordsPerPage);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Statistics statistics = statisticsMapper.extractFromResultSet(rs);
                statistics = statisticsMapper.makeUnique(statisticsHashMap, statistics);
                foods.add(statistics);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return foods;
    }

    @Override
    public int getNumberOfRows(int userId, Date date) {
        int numOfRows = 0;
        try(PreparedStatement pstm = connection.prepareStatement(GET_NUMBER_ROWS_STATISTICS)) {
            pstm.setInt(1, userId);
            pstm.setDate(2, date);
            pstm.setInt(3, userId);
            pstm.setDate(4, date);
            pstm.setInt(5, userId);
            pstm.setDate(6, date);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                numOfRows += rs.getInt(ID_ALL);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return numOfRows;
    }

    @Override
    public void update(Statistics statistics) {

    }

    @Override
    public void delete(int id) {

        try(PreparedStatement pstm = connection.prepareStatement(DELETE_FROM_STATISTICS)) {

            pstm.setInt(1, id);
            pstm.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
