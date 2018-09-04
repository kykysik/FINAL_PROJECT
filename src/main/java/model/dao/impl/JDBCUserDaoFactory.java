package model.dao.impl;

import model.dao.*;

import model.utils.PortionsUtils;
import model.utils.ProductsUtils;
import model.utils.UserUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUserDaoFactory extends UserDaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao findByUserName() {
        return new UserUtils(getConnection());
    }
    @Override
    public UserDao findForCookie() {
        return new UserUtils(getConnection());
    }

    @Override
    public UserDao create() {
        return new UserUtils(getConnection());
    }

    @Override
    public UserDao find() {
        return new UserUtils(getConnection());
    }

    private Connection getConnection(){

        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.getStackTrace();
            throw new RuntimeException(e);
        }

    }
}
