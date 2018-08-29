package model.utils;

import model.dao.StatisticsDao;
import model.entity.Statistics;
import model.entity.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StatisticsUtils implements StatisticsDao {
    Connection connection;
    User user;

    public StatisticsUtils(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Statistics stat) {
        user = new User();

       String sql = "INSERT INTO statistics(user_login,food_name, fats, proteins, carbohydrates, calories, amount, date)" +
  "SELECT ? ,name, fats, proteins, carbohydrates, calories, ?, ? FROM food WHERE name = ?";

        try(PreparedStatement pstm = connection.prepareStatement(sql)) {

                /*
                * Тут нужно сделать что-то с параметрами 1,2,3.
                * Date через геттер нужно преобразовывать в нужный формат: 2018-02-23. Как-то так.
                * */
            pstm.setString(1,user.getLogin());
            pstm.setFloat(2,user.getLifeActivity());
            pstm.setDate(3,user.getBirthDate());
            pstm.setString(4,"name");
            pstm.executeUpdate();

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
    public List<Statistics> findAll() {
       /* String sql = "SELECT * FROM statistics ORDER BY id DESC";

        List<Statistics> foods = new ArrayList<>();

        try(PreparedStatement pstm = connection.prepareStatement(sql)) {

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                //  int id = rs.getInt("id");
                String name = rs.getString("name");
                Float proteins = rs.getFloat("proteins");
                Float carbohydrates = rs.getFloat("carbohydrates");
                Float fats = rs.getFloat("fats");
                Float calories = rs.getFloat("calories");
                foods.add(new Statistics(name, proteins, carbohydrates, fats, calories));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foods;*/
       return null;
    }

    @Override
    public void update(Statistics statistics) {

    }

    @Override
    public void delete(Statistics statistics) {

        String sql = "DELETE * FROM statistics WHERE food_name = ?";

        try(PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, statistics.getFoodName());
            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {

    }

}
