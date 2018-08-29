package model.utils;

import model.dao.ProductsDao;
import model.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ProductsUtils implements ProductsDao {

    Connection connection;

    public ProductsUtils(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Product product) {
        String sql = "INSERT INTO products(name, fats, proteins, carbohydrates, calories) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, product.getName());
            pstm.setFloat(2, product.getFats());
            pstm.setFloat(3, product.getProteins());
            pstm.setFloat(4, product.getCarbohydrates());
            pstm.setFloat(4, product.getCalories());

            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {return null;}

    @Override
    public List<Product> findAll(int currentPage, int recordsPerPage) {
        String sql = "SELECT id, name,proteins,carbohydrates,fats,calories FROM products LIMIT ?, ?";
        int start = currentPage * recordsPerPage - recordsPerPage;
        List<Product> foods = new ArrayList<>();

        try(PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, start);
            pstm.setInt(2, recordsPerPage);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Float proteins = rs.getFloat("proteins");
                Float carbohydrates = rs.getFloat("carbohydrates");
                Float fats = rs.getFloat("fats");
                Float calories = rs.getFloat("calories");
                foods.add(new Product(id, name, proteins, carbohydrates, fats, calories));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foods;
    }

    @Override
    public int getNumberOfRows() {

        int numOfRows = 0;
         String sql = "SELECT COUNT(id) as id FROM products";
        try(PreparedStatement pstm = connection.prepareStatement(sql)) {

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                numOfRows = rs.getInt("id");
            }

        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return numOfRows;
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE products SET name = ?, proteins = ?, fats = ?, carbohydrates = ?, calories = ? WHERE  id = ?";
        try(PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, product.getName());
            pstm.setFloat(2, product.getProteins());
            pstm.setFloat(3, product.getFats());
            pstm.setFloat(4, product.getCarbohydrates());
            pstm.setFloat(2, product.getCalories());
            pstm.setInt(3, product.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Product product) {
        String sql = "DELETE FROM products WHERE id = ? ";

        try(PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, product.getId());
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
