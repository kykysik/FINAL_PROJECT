package model.dao;

import model.dao.impl.ConnectionPoolHolder;
import model.utils.StatisticsUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCStatisticsDaoFactory extends StatisticsDaoFactory {

    @Override
    public StatisticsDao findAll() {
        return new StatisticsUtils(getConnection());
    }

    @Override
    public StatisticsDao getNumberOfRows() {
        return new StatisticsUtils(getConnection());
    }

    @Override
    public StatisticsDao deleteAfterInsert()  {
        return new StatisticsUtils(getConnection());
    }

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    private Connection getConnection(){

        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.getStackTrace();
            throw new RuntimeException(e);
        }

    }
}
