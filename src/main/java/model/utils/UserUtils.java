package model.utils;

import model.dao.UserDao;
import model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserUtils implements UserDao {

    Connection connection;

    public UserUtils(Connection connection) {
        this.connection = connection;
    }



    @Override
    public void create(User user) {

        String sql = "INSERT INTO user(login, password, second_name, first_name, middle_name, gender, " +
                "birth_date, life_activity, height, weight) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, user.getLogin());
            pstm.setString(2, user.getPassword());
            pstm.setString(3, user.getSecondName());
            pstm.setString(4, user.getFirstName());
            pstm.setString(5, user.getMiddleName());
            pstm.setString(6, user.getGender());
            pstm.setDate(7, user.getBirthDate()); // sql.Date
            pstm.setDouble(8, user.getLifeActivity());
            pstm.setDouble(9, user.getHeight());
            pstm.setDouble(10, user.getWeight());
            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> findByUserName(User user) { // Должен работать, по идее


        String sql = "Select login FROM  user WHERE login = ?";

        ArrayList<String> list = new ArrayList<>();

        try(PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, user.getLogin() );

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String login = rs.getString("login");
                System.out.println("KAK");
                System.out.println(login);
                list.add(login);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public User find(String login, String password) { // нужно будет сделать страницу, на которой юзер смотрит инфу о себе.
        String sql = "SELECT login, first_name, second_name, middle_name, gender, life_activity," +
                "height, weight, birth_date/*, norm_calories*/ FROM user WHERE login = ? AND password = ?";

        try(PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, login);
            pstm.setString(2, password);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                User user = new User();
                String gender = rs.getString("gender");
                String firstName = rs.getString("first_name");
                String secondName = rs.getString("second_name");
                String middleName = rs.getString("middle_name");
                String lifeActivity = rs.getString("life_activity");
                String height = rs.getString("height");
                String weight = rs.getString("weight");
                Date date = Date.valueOf(rs.getString("birth_date"));
/*
                String normCalories = rs.getString("norm_calories");
*/

                user.setLogin(login);
                user.setPassword(password);
                user.setGender(gender);
                user.setFirstName(firstName);
                user.setMiddleName(middleName);
                user.setSecondName(secondName);
                user.setLifeActivity(Float.parseFloat(lifeActivity));
                user.setHeight(Float.parseFloat(height));
                user.setWeight(Float.parseFloat(weight));
                user.setBirthDate(date);
/*
                user.setNormCalories(Float.parseFloat(normCalories));
*/
                return user;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Список юзеров может посмотреть админ.
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User user) {

        String sql = "DELETE * FROM user WHERE login = ?";
        try(PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, user.getLogin());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /*
    public List<Car> findAllPortions() {
        List<Car> resultList = new ArrayList<>();
        Map<Integer,Driver> drivers = new HashMap<>();
        Map<Integer,Car> cars = new HashMap<>();
        try (Statement ps = connection.createStatement()){
            ResultSet rs = ps.executeQuery(
                    "select * from car " +
                        "left join car_driver on " +
                            "car.idcar = car_driver.car_idcar " +
                        "left join driver on " +
                            "car_driver.driver_driver_id = " +
                            "driver.driver_id");
            while ( rs.next() ){
                Car car = extractFromResultSet(rs);
                Driver driver =
                        JDBCDriverDao.extractFromResultSet(rs);
                car = makeUniqueCar( cars, car);
                driver = makeUniqueDriver(drivers,driver);
                car.getDrivers().add(driver);
                driver.getCars().add(car);
                System.out.println(driver);

                resultList.add(car);
            }
        } catch (ExceptionCommand e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }


    private Car makeUniqueCar(Map<Integer, Car> cars,  Car car) {
        cars.putIfAbsent(car.getIdCar(), car);
        return cars.get(car.getIdCar());
    }

    private Driver makeUniqueDriver(
            Map<Integer, Driver> drivers, Driver driver) {
        drivers.putIfAbsent(driver.getIddriver(),
                driver);
        return drivers.get(driver.getIddriver());
    }*/

        @Override
        public void close() {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

}
