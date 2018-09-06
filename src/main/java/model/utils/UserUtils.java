package model.utils;

import model.dao.impl.user.UserDao;
import model.dao.mapper.user.UserMapImpl;
import model.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static view.SqlConstant.*;

public class UserUtils implements UserDao {
    private static final String COUNT = "count";
    private static final Logger logger = Logger.getLogger(UserUtils.class);

    UserMapImpl userMapper = new UserMapImpl();
    Connection connection;

    public UserUtils(Connection connection) {
        this.connection = connection;
    }



    @Override
    public boolean create(User user) {

        try(PreparedStatement pstm1 = connection.prepareStatement(USER_PRE_CREATE)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            pstm1.setString(1, user.getLogin());

            ResultSet rs = pstm1.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(COUNT);
                if(count == 0) {

                    try(PreparedStatement pstm = connection.prepareStatement(USER_CREATE)) {

                        pstm.setString(1, user.getLogin());
                        pstm.setString(2, user.getPassword());
                        pstm.setString(3, user.getSecondName());
                        pstm.setString(4, user.getFirstName());
                        pstm.setString(5, user.getMiddleName());
                        pstm.setString(6, user.getGender());
                        pstm.setDate(7, Date.valueOf(user.getBirthDate()));
                        pstm.setDouble(8, user.getLifeActivity());
                        pstm.setDouble(9, user.getHeight());
                        pstm.setDouble(10, user.getWeight());
                        pstm.setDouble(11, user.getNormCalories());
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
    public List<String> findByUserName(User user) {
        ArrayList<String> list = new ArrayList<>();


        try(PreparedStatement pstm = connection.prepareStatement(FIND_BY_USER_NAME)) {

            pstm.setString(1, user.getLogin() );

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                user = userMapper.extractFromResult(rs);
                list.add(user.getLogin());
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return list;
    }

    @Override
    public User find(String login, String password) {
        Map<Integer, User> users = new HashMap<>();

        try(PreparedStatement pstm = connection.prepareStatement(FIND_USER)) {

            pstm.setString(1, login);
            pstm.setString(2, password);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                model.entity.User user = userMapper.extractFromResultSet(rs);
                user = userMapper.makeUnique(users, user);
                return user;
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> findAll(int currentPage, int recordsPerPage) {
        return null;
    }

    @Override
    public int getNumberOfRows() {
        return 0;
    }

    public User findForCookie(String login) {
        Map<Integer, User> users = new HashMap<>();

        try(PreparedStatement pstm = connection.prepareStatement(FIND_FOR_COOKIE)) {
            pstm.setString(1, login);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                User user = userMapper.extractFromResultSet(rs);
                user = userMapper.makeUnique(users, user);
                return user;
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(int id) {

        try(PreparedStatement pstm = connection.prepareStatement(DELETE_USER)) {

            pstm.setInt(1, id);

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
