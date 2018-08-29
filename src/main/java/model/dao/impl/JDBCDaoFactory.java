package model.dao.impl;

import model.dao.*;

import model.utils.PortionsUtils;
import model.utils.ProductsUtils;
import model.utils.UserUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public ProductsDao findAll(){
        return new ProductsUtils(getConnection());
    }

    @Override
    public PortionsDao findAllPortions(){
        return new PortionsUtils(getConnection());
    }

    @Override
    public UserDao findByUserName() {
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

    @Override
    public ProductsDao getNumberOfRows() {
        return new ProductsUtils(getConnection());
    }

    @Override
    public PortionsDao getNumberOfRowsPortions() {
        return new PortionsUtils(getConnection());
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
