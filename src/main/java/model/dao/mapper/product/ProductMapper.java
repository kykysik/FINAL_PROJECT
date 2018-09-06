package model.dao.mapper.product;

import model.dao.mapper.ObjectMapper;
import model.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ProductMapper extends ObjectMapper<Product> {
    Product extractFromResult(ResultSet rs) throws SQLException;
}
