package model.dao.mapper.portion;


import model.entity.Portions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PortionMapImpl implements PortionMapper {
    private static final String AMOUNT = "amount";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String CALORIES = "calories";


    @Override
    public Portions extractFromResultSet(ResultSet rs) throws SQLException {
        Portions portions = new Portions();
        portions.setId(rs.getInt(ID));
        portions.setName(rs.getString(NAME));
        portions.setCalories(rs.getFloat(CALORIES));
        return portions;
    }

    @Override
    public Portions extractFromResultAmount(ResultSet rs) throws SQLException {
        Portions portions = new Portions();
        portions.setId(rs.getInt(ID));
        portions.setName(rs.getString(NAME));
        portions.setCalories(rs.getFloat(CALORIES));
        portions.setCalories(rs.getFloat(AMOUNT));
        return portions;
    }


    @Override
    public Portions extractFromResultId(ResultSet rs) throws SQLException {
        Portions portion = new Portions();
        portion.setId(rs.getInt(ID));
        return portion;
    }

    @Override
    public Portions makeUnique(Map<Integer, Portions> cache, Portions portions) {
        cache.putIfAbsent(portions.getId(), portions);
        return cache.get(portions.getId());
    }
}
