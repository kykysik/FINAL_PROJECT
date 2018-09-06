package model.dao.impl.product;

import model.dao.impl.ConnectionPoolHolder;
import model.utils.ProductsUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCProductDaoFactory extends ProductDaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public ProductsDao findAll() {
        return new ProductsUtils(getConnection());
    }

    @Override
    public ProductsDao getNumberOfRows() {
        return new ProductsUtils(getConnection());
    }

    @Override
    public ProductsDao delete() {
        return new ProductsUtils(getConnection());
    }

    @Override
    public ProductsDao find() {
        return new ProductsUtils(getConnection());
    }

    @Override
    public ProductsDao update() {
        return new ProductsUtils(getConnection());
    }

    @Override
    public ProductsDao findAllById() {
        return new ProductsUtils(getConnection());
    }

    @Override
    public ProductsDao create()  {
        return new ProductsUtils(getConnection());
    }

    @Override
    public ProductsDao eatProduct()   {
        return new ProductsUtils(getConnection());
    }

    @Override
    public ProductsDao findAllByDate()    {
        return new ProductsUtils(getConnection());
    }

    @Override
    public ProductsDao deleteUserProduct()     {
        return new ProductsUtils(getConnection());
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
