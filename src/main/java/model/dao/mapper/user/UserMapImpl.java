package model.dao.mapper.user;

import model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

public class UserMapImpl implements UserMapper {
    private static final String LOGIN = "login";
    private static final String ID = "id";
    private static final String GENDER = "gender";
    private static final String FIRST_NAME = "first_name";
    private static final String PASSWORD = "password";
    private static final String SECOND_NAME = "second_name";
    private static final String MIDDLE_NAME = "middle_name";
    private static final String ACTIVITY = "life_activity";
    private static final String HEIGHT = "height";
    private static final String WEIGHT = "weight";
    private static final String BIRTH_DATE = "birth_date";
    private static final String NORM_CALORIES = "norm_calories";

    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User.Builder builder = new User.Builder();
        builder.setId(rs.getInt(ID));
        builder.setLogin(rs.getString(LOGIN));
        builder.setGender(rs.getString(GENDER));
        builder.setPassword(rs.getString(PASSWORD));
        builder.setNormCalories(rs.getFloat(NORM_CALORIES));
        builder.setWeight(rs.getFloat(WEIGHT));
        builder.setHeight(rs.getFloat(HEIGHT));
        builder.setActivity(rs.getFloat(ACTIVITY));
        builder.setSecondName(rs.getString(SECOND_NAME));
        builder.setFirstName(rs.getString(FIRST_NAME));
        builder.setMiddleName(rs.getString(MIDDLE_NAME));
        builder.setBirthDate(LocalDate.parse(rs.getString(BIRTH_DATE)));
        return builder.build();
    }

    @Override
    public User extractFromResult(ResultSet rs) throws SQLException {
        User user = new User();
        user.setLogin(rs.getString(LOGIN));
        return user;
    }

    @Override
    public User makeUnique(Map<Integer, User> cache,
                                        User user) {
        cache.putIfAbsent(user.getId(), user);
        return cache.get(user.getId());
    }
}
