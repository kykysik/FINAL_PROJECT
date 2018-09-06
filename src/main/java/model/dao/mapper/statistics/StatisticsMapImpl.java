package model.dao.mapper.statistics;

import model.entity.Statistics;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class StatisticsMapImpl implements StatisticsMapper {

    private static final String AMOUNT = "amount";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String CALORIES = "calories";
    private static final String TYPE = "type";


    @Override
    public Statistics extractFromResultSet(ResultSet rs) throws SQLException {
        Statistics statistics = new Statistics();
        statistics.setId(rs.getInt(ID));
        statistics.setType(rs.getString(TYPE));
        statistics.setName(rs.getString(NAME));
        statistics.setCalories(rs.getFloat(CALORIES));
        statistics.setAmount(rs.getInt(AMOUNT));
        return statistics;
    }

    @Override
    public Statistics makeUnique(Map<Integer, Statistics> cache, Statistics statistics) {
        cache.putIfAbsent(statistics.getId(), statistics);
        return cache.get(statistics.getId());
    }
}
