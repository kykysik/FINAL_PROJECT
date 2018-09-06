package model.utils;

import model.dao.impl.portion.PortionsDao;
import model.dao.mapper.portion.PortionMapImpl;
import model.dao.mapper.portion.PortionMapper;
import model.entity.Portions;
import model.entity.Product;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static view.SqlConstant.*;

public class PortionsUtils implements PortionsDao {
    private static final String COUNT = "count";
    private static final String AMOUNT = "amount";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String CALORIES = "calories";
    private static final String PRODUCT_ID = "productId";
    private static final String PRODUCT_NAME = "productName";
    private static final String PR_ID = "prId";
    private static final String PR_NAME = "prName";
    private static final String PROTEINS = "proteins";
    private static final String CARBOHYDRATES = "carbohydrates";
    private static final String FATS = "fats";
    private static final String PRODUCT_CALORIES = "productCalories";
    private static final Logger logger = Logger.getLogger(PortionsUtils.class);
    Connection connection;
    PortionMapper portionMapper = new PortionMapImpl();

    public PortionsUtils(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Portions entity) {
        try(PreparedStatement pstm = connection.prepareStatement(PORTIONS_PRE_CREATE)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            pstm.setString(1, entity.getName());

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(COUNT);
                if(count == 0) {
                    try(PreparedStatement pstm1 = connection.prepareStatement(PORTIONS_CREATE_SQL)) {
                        pstm1.setString(1, entity.getName());
                        pstm1.setFloat(2, entity.getCalories());
                        pstm1.executeUpdate();
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
    public void createPortionProduct(List product, List amount, int portionId) {
        try {
            connection.setAutoCommit(false);
                try (PreparedStatement pstm = connection.prepareStatement(CREATE_PORTION_PRODUCT)) {
                    for(int i = 0; i < product.size(); i++) {
                        Product prod = (Product) product.get(i);
                        pstm.setInt(1, portionId);
                        pstm.setInt(2, prod.getId());
                        pstm.setInt(3, (Integer) amount.get(i));
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
    public Portions findPortion(Portions portions) {
        Map<Integer, Portions> portionsMap = new HashMap<>();

        try(PreparedStatement pstm = connection.prepareStatement(FIND_PORTION)) {
                pstm.setString(1, portions.getName());
                ResultSet rs = pstm.executeQuery();
                if (rs.next()) {
                    portions = portionMapper.extractFromResultId(rs);
                    portions = portionMapper.makeUnique(portionsMap, portions);
                    return portions;
                }

            } catch (SQLException e) {
                 logger.error(e.getMessage());
            }
    return portions;
    }

    @Override
    public List<Portions> findAll(int currentPage, int recordsPerPage) {
        Map<Integer, Portions> portionsMap = new HashMap<>();
        int start = currentPage * recordsPerPage - recordsPerPage;
        List<Portions> list = new ArrayList<>();

        try(PreparedStatement pstm = connection.prepareStatement(FIND_ALL_PORTIONS)) {
            pstm.setInt(1, start);
            pstm.setInt(2, recordsPerPage);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Portions portions = portionMapper.extractFromResultSet(rs);
                portions = portionMapper.makeUnique(portionsMap, portions);
                list.add(portions);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Portions> findAllByDate(int currentPage, int recordsPerPage, int userId, Date date) {
        Map<Integer, Portions> portionsMap = new HashMap<>();

        int start = currentPage * recordsPerPage - recordsPerPage;
        List<Portions> foods = new ArrayList<>();

        try(PreparedStatement pstm = connection.prepareStatement(FIND_ALL_BY_DATE_PORTIONS)) {
            pstm.setInt(1, userId);
            pstm.setDate(2, date);
            pstm.setInt(3, start);
            pstm.setInt(4, recordsPerPage);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Portions portions = portionMapper.extractFromResultAmount(rs);
                portions = portionMapper.makeUnique(portionsMap, portions);
                foods.add(portions);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return foods;
    }

    @Override
    public Map find(int id) {

        Map<String, List> map = new HashMap<>();
        List<Integer> poId = new ArrayList<Integer>();
        List<Integer> prId = new ArrayList<Integer>();
        List<String> prName = new ArrayList<String>();
        List<Float> proteins = new ArrayList<Float>();
        List<Float> carbohydrates = new ArrayList<Float>();
        List<Float> fats = new ArrayList<Float>();
        List<Float> prCalories = new ArrayList<Float>();
        List<Integer> ppAmount = new ArrayList<Integer>();

        try(PreparedStatement pstm = connection.prepareStatement(FIND_PORTIONS)) {
            pstm.setInt(1, id);

            poId.add(id);
            map.put(ID, poId);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String portionsName = rs.getString(NAME);
                Integer productId = rs.getInt(PR_ID);
                Float portionCalories = rs.getFloat(CALORIES);
                String productsName = rs.getString(PR_NAME);
                Float productsProteins = rs.getFloat(PROTEINS);
                Float productsCarbohydrates = rs.getFloat(CARBOHYDRATES);
                Float productsFats = rs.getFloat(FATS);
                Float productsCalories= rs.getFloat(PRODUCT_CALORIES);
                Integer Amount= rs.getInt(AMOUNT);
                prId.add(productId);
                prName.add(productsName);
                proteins.add(productsProteins);
                carbohydrates.add(productsCarbohydrates);
                fats.add(productsFats);
                prCalories.add(productsCalories);
                ppAmount.add(Amount);
                map.put(AMOUNT, ppAmount);
                map.put(CALORIES, Collections.singletonList(portionCalories));
                map.put(NAME, Collections.singletonList(portionsName));
                map.put(PRODUCT_ID, prId);
                map.put(PRODUCT_NAME, prName);
                map.put(PROTEINS, proteins);
                map.put(CARBOHYDRATES, carbohydrates);
                map.put(FATS, fats);
                map.put(PRODUCT_CALORIES, prCalories);

            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return map;
    }

    @Override
    public int getNumberOfRows() {

        int numOfRows = 0;
        try(PreparedStatement pstm = connection.prepareStatement(GET_NUMBER_PORTIONS)) {

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
    public void eatPortion(List portionsId, List amount, int userId, Date date) {
        try {
            connection.setAutoCommit(false);
            try(PreparedStatement pstm = connection.prepareStatement(EAT_PORTIONS)) {
                for(int i = 0; i < portionsId.size(); i++) {
                    pstm.setInt(1, userId);
                    pstm.setInt(2, (Integer) portionsId.get(i));
                    pstm.setInt(3, (Integer) amount.get(i));
                    pstm.setDate(4, date);
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
    public void editPortion(List count, List id, int portionId) {
        try {
            connection.setAutoCommit(false);
                try(PreparedStatement pstm = connection.prepareStatement(EDIT_PORTIONS)) {
                    for(int i = 0; i < id.size(); i++) {
                        pstm.setInt(1, portionId);
                        pstm.setInt(2, (Integer) id.get(i));
                        pstm.setInt(3, (Integer) count.get(i));
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
    public void update(Portions portions) {
        try(PreparedStatement pstm = connection.prepareStatement(UPDATE_PORTIONS)) {
            pstm.setString(1, portions.getName());
            pstm.setFloat(2, portions.getCalories());
            pstm.setInt(3, portions.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void deleteById(int portionId, int productId) {
        try(PreparedStatement pstm = connection.prepareStatement(DELETE_BY_ID_PORTIONS)) {
            pstm.setInt(1, portionId);
            pstm.setInt(2, productId);
            pstm.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void deleteUserPortion(int id) {
        try(PreparedStatement pstm = connection.prepareStatement(DELETE_USER_PORTIONS)) {
            pstm.setInt(1, id);

            pstm.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement pstm = connection.prepareStatement(DELETE_PORTIONS)) {
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
