package model.dao.mapper;

import model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/*public class StudentMapper implements ObjectMapper<User> {


    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User student = new User();
        student.setId(rs.getInt("idstuden"));
        student.setName(rs.getString("studen.name"));
        student.setGroupe(rs.getInt("group"));
        return student;
    }

    @Override
    public User makeUnique(Map<Integer, User> cache,
                           User student) {
        cache.putIfAbsent(student.getId(), student);
        return cache.get(student.getId());
    }
}*/
