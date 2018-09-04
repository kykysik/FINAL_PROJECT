package model.utils;

import model.dao.StatisticsDao;
import model.entity.Statistics;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatisticsUtils implements StatisticsDao {
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

            String userProductsSql = "" +
                    "INSERT INTO statistics(name, proteins, fats, carbohydrates, calories, amount, date, user_id) " +
                    "SELECT products.name, proteins, fats, carbohydrates, calories, u.amount, u.date, user_id  " +
                    "FROM products " +
                    "JOIN user_products u ON products.id = u.products_id " +
                    "JOIN user u2 ON u.user_id = u2.id WHERE u.date != ? " +
                    "UNION ALL SELECT p.name, proteins, fats, carbohydrates, p.calories, u.amount, u.date, user_id " +
                    "FROM user " +
                    "JOIN user_portions u ON user.id = u.portions_id " +
                    "JOIN portions p ON u.portions_id = p.id " +
                    "JOIN portions_products product ON p.id = product.portions_id " +
                    "JOIN products p2 ON product.products_id = p2.id WHERE u.date != ?";

            String delProducts = "DELETE  FROM user_products WHERE date <> ?";

            String delPortions = "DELETE FROM user_portions WHERE date <> ?";

            try(PreparedStatement pstm = connection.prepareStatement(userProductsSql);
            PreparedStatement pstm1 = connection.prepareStatement(delProducts);
                PreparedStatement pstm2 = connection.prepareStatement(delPortions)) {

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

                e.printStackTrace();
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<Statistics> find(Statistics statistics) { // Вывести список food
        return null;
    }

    /*
    * Что нужно в статистике?
    * Запихнуть в статистику с food.
    * Менять там ничего нельзя.
    * Выбрать все, где текущий юзер = юзер с таблицы и текущая дата = дате с таблицы. !?
    *
    *
    *
    * */

    @Override
    public List<Statistics> findAll(int currentPage, int recordsPerPage, int userId, Date date) {
        String sql = "SELECT user_id, id, name, calories, amount, date, `type` FROM " +
                "(SELECT user_id, u.id, name, calories, amount, date, 'portion' as type" +
                " FROM portions JOIN user_portions u ON portions.id = u.portions_id " +
                "JOIN user u2 ON u.user_id = u2.id " +
                "UNION ALL " +
                "SELECT user_id, product.id, name, calories, amount, date, 'product' as type FROM products " +
                "JOIN user_products product ON products.id = product.products_id " +
                "JOIN user u3 ON product.user_id = u3.id " +
                "UNION ALL " +
                "SELECT user_id, statistics.id, name, calories, amount, date, 'statistics' as type FROM statistics) X WHERE user_id = ? AND date = ? LIMIT ?, ?";

        int start = currentPage * recordsPerPage - recordsPerPage;
        List<Statistics> foods = new ArrayList<>();

        try(PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, userId);
            pstm.setDate(2, date);
            pstm.setInt(3, start);
            pstm.setInt(4, recordsPerPage);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                String name = rs.getString("name");
                Float calories = rs.getFloat("calories");
                int amount  = rs.getInt("amount");

                Statistics statistics = new Statistics(id, name, calories, amount, date);
                statistics.setType(type);
                foods.add(statistics);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return foods;
    }

    @Override
    public int getNumberOfRows(int userId, Date date) {
        int numOfRows = 0;
        String sql = "SELECT COUNT(products_id) as idAll, user_id, date FROM user_products WHERE user_id = ? AND date = ? UNION ALL " +
                "SELECT COUNT(portions_id) as idAll, user_id, date FROM user_portions WHERE user_id = ? AND date = ? " +
                "UNION ALL SELECT COUNT(id) as idAll, user_id, date FROM statistics WHERE user_id = ? AND date = ?  ";
        try(PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, userId);
            pstm.setDate(2, date);
            pstm.setInt(3, userId);
            pstm.setDate(4, date);
            pstm.setInt(5, userId);
            pstm.setDate(6, date);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                numOfRows += rs.getInt("idAll");
            }

        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
        System.out.println(numOfRows);
        return numOfRows;
    }

    @Override
    public void update(Statistics statistics) {

    }

    @Override
    public void delete(int id) {

        String sql = "DELETE FROM statistics WHERE id = ?";

        try(PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setInt(1, id);
            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
