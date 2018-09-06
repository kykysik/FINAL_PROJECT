package model.dao.mapper.product;

import model.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ProductMapImpl implements ProductMapper {

    private static final String AMOUNT = "amount";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String CALORIES = "calories";
    private static final String PROTEINS = "proteins";
    private static final String CARBOHYDRATES = "carbohydrates";
    private static final String FATS = "fats";

    @Override
    public Product extractFromResultSet(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setName(rs.getString(NAME));
        product.setCalories(rs.getFloat(CALORIES));
        product.setProteins(rs.getFloat(PROTEINS));
        product.setCarbohydrates(rs.getFloat(CARBOHYDRATES));
        product.setFats(rs.getFloat(FATS));
        product.setId(rs.getInt(ID));
        return product;
    }

    @Override
    public Product extractFromResult(ResultSet rs) throws SQLException {
       Product product = new Product();
        product.setName(rs.getString(NAME));
        product.setCalories(rs.getFloat(CALORIES));
        product.setId(rs.getInt(ID));
        product.setAmount(rs.getInt(AMOUNT));
        return product;
    }

    @Override
    public Product makeUnique(Map<Integer, Product> cache,
                                           Product product) {
        cache.putIfAbsent(product.getId(), product);
        return cache.get(product.getId());
    }
}
