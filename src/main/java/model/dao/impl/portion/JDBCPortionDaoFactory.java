package model.dao.impl.portion;

import model.dao.impl.ConnectionPoolHolder;
import model.utils.PortionsUtils;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCPortionDaoFactory extends PortionDaoFactory {


    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public PortionsDao findAll() {
        return new PortionsUtils(getConnection());
    }

    @Override
    public PortionsDao getNumberOfRows() {
        return new PortionsUtils(getConnection());
    }

    @Override
    public PortionsDao delete() {return new PortionsUtils(getConnection()); }

    @Override
    public PortionsDao find() {
        return new PortionsUtils(getConnection());
    }

    @Override
    public PortionsDao editPortion() {
        return new PortionsUtils(getConnection());
    }

    @Override
    public PortionsDao deleteById() { return new PortionsUtils(getConnection());}

    @Override
    public PortionsDao createPortionProduct() { return new PortionsUtils(getConnection());}

    @Override
    public PortionsDao create() { return new PortionsUtils(getConnection());}

    @Override
    public PortionsDao findPortion() { return new PortionsUtils(getConnection());}

    @Override
    public PortionsDao eatPortion() { return new PortionsUtils(getConnection());}

    @Override
    public PortionsDao findAllByDate() { return new PortionsUtils(getConnection());}

    @Override
    public PortionsDao deleteUserPortion() { return new PortionsUtils(getConnection());}

    private Connection getConnection(){

        try {
            return dataSource.getConnection();
        } catch (SQLException e) {

            e.getStackTrace();
            throw new RuntimeException(e);
        }

    }
}
