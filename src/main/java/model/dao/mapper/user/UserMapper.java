package model.dao.mapper.user;

import model.dao.mapper.ObjectMapper;
import model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserMapper extends ObjectMapper<User> {
    User extractFromResult(ResultSet rs) throws SQLException;

}
