package model.utils;

import model.dao.impl.product.ProductsDao;
import model.dao.mapper.product.ProductMapImpl;
import model.entity.Product;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static view.SqlConstant.*;


public class ProductsUtils implements ProductsDao {
    private static final String COUNT = "count";
    private static final String ID = "id";
    private static final Logger logger = Logger.getLogger(ProductsUtils.class);

    ProductMapImpl productMapper = new ProductMapImpl();
    Connection connection;

    public ProductsUtils(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Product product) {
        try(PreparedStatement pstm1 = connection.prepareStatement(PRODUCTS_PRE_CREATE)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            pstm1.setString(1, product.getName());

            ResultSet rs = pstm1.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(COUNT);
                if(count == 0) {
                        try(PreparedStatement pstm = connection.prepareStatement(PRODUCTS_CREATE_SQL)) {

                            pstm.setString(1, product.getName());
                            pstm.setFloat(2, product.getFats());
                            pstm.setFloat(3, product.getProteins());
                            pstm.setFloat(4, product.getCarbohydrates());
                            pstm.setFloat(5, product.getCalories());

                            pstm.executeUpdate();
                            connection.commit();
                            return true;
                        } catch (SQLException e) {
                            connection.rollback();
                            logger.error(e.getMessage());
                        }
                }

            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    @Override
    public void eatProduct(List productsId, List amount, int userId, Date date) {
        try {
            connection.setAutoCommit(false);
            try(PreparedStatement pstm = connection.prepareStatement(EAT_PRODUCTS)) {
                for(int i = 0; i < productsId.size(); i++) {
                    pstm.setInt(1, userId);
                    pstm.setInt(2, (Integer) productsId.get(i));
                    pstm.setDate(3, date);
                    pstm.setInt(4, (Integer) amount.get(i));
                    pstm.addBatch();
                }

                pstm.executeBatch();
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
    public List<Product> findAllByDate(int currentPage, int recordsPerPage, int userId, Date date) {

        int start = currentPage * recordsPerPage - recordsPerPage;
        List<Product> foods = new ArrayList<>();
        Map<Integer, Product> products = new HashMap<>();

        try(PreparedStatement pstm = connection.prepareStatement(FIND_ALL_BY_DATE_PRODUCTS)) {
            pstm.setInt(1, userId);
            pstm.setDate(2, date);
            pstm.setInt(3, start);
            pstm.setInt(4, recordsPerPage);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Product product = productMapper.extractFromResult(rs);
                product = productMapper.makeUnique(products, product);
                foods.add(product);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());

        }
        return foods;
    }

    @Override
    public List<Product> findAllById(List productId) {
        Map<Integer,Product> products = new HashMap<>();
        List<Product> list = new ArrayList<>();

        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            StringBuilder query = new StringBuilder();
            query.append(FIND_ALL_BY_ID_SQL);
            int queryCount = productId.size();
            // If we have more than one parameter add a UNION clause:
            while (queryCount-- > 1) {
                query.append(" UNION ").append(FIND_ALL_BY_ID_SQL);
            }
                try (PreparedStatement pstm = connection.prepareStatement(query.toString())) {
                    int paramIdx = 1;
                    for (Object aProductId : productId) {
                        pstm.setInt(paramIdx++, (Integer) aProductId);
                    }

                    ResultSet rs = pstm.executeQuery();
                    while (rs.next()) {
                        Product product = productMapper.extractFromResultSet(rs);
                        product = productMapper.makeUnique(products, product);
                        list.add(product);
                    }

                    connection.commit();

                } catch (SQLException e) {
                    connection.rollback();
                    logger.error(e.getMessage());
                }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return list;
    }

    @Override
    public List<Product> findAll(int currentPage, int recordsPerPage) {
        int start = currentPage * recordsPerPage - recordsPerPage;
        List<Product> foods = new ArrayList<>();
        Map<Integer, Product> products = new HashMap<>();

        try(PreparedStatement pstm = connection.prepareStatement(FIND_ALL_PRODUCTS)) {
            pstm.setInt(1, start);
            pstm.setInt(2, recordsPerPage);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Product product = productMapper.extractFromResultSet(rs);
                product = productMapper.makeUnique(products, product);
                foods.add(product);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return foods;
    }

    @Override
    public int getNumberOfRows() {

        int numOfRows = 0;
        try(PreparedStatement pstm = connection.prepareStatement(GET_NUMBER_PRODUCTS)) {

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                numOfRows = rs.getInt(ID);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return numOfRows;
    }

    @Override
    public void update(Product product) {
        try(PreparedStatement pstm = connection.prepareStatement(UPDATE_PRODUCTS)) {
            pstm.setString(1, product.getName());
            pstm.setFloat(2, product.getProteins());
            pstm.setFloat(3, product.getFats());
            pstm.setFloat(4, product.getCarbohydrates());
            pstm.setFloat(5, product.getCalories());
            pstm.setInt(6, product.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public Product find(int id) {
        Map<Integer, Product> products = new HashMap<>();
        Product product = new Product();
        try(PreparedStatement pstm = connection.prepareStatement(FIND_PRODUCTS)) {
            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                product = productMapper.extractFromResultSet(rs);
                product = productMapper.makeUnique(products, product);

                return product;
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return product;
    }

    @Override
    public void delete(int id) {

        try(PreparedStatement pstm = connection.prepareStatement(DELETE_PRODUCTS)) {
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void deleteUserProduct(int id) {

        try(PreparedStatement pstm = connection.prepareStatement(DELETE_USER_PRODUCTS)) {
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
