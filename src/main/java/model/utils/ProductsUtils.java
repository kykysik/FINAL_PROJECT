package model.utils;

import model.dao.ProductsDao;
import model.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class ProductsUtils implements ProductsDao {

    Connection connection;

    public ProductsUtils(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Product product) {
        String preSql = "SELECT COUNT(1) AS count FROM products WHERE name = ?";
        try(PreparedStatement pstm1 = connection.prepareStatement(preSql)) {
            connection.setAutoCommit(false);

            pstm1.setString(1, product.getName());

            ResultSet rs = pstm1.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                if(count == 0) {
                        String sql = "INSERT INTO products(name, fats, proteins, carbohydrates, calories) VALUES (?, ?, ?, ?, ?)";
                        try(PreparedStatement pstm = connection.prepareStatement(sql)) {

                            pstm.setString(1, product.getName());
                            pstm.setFloat(2, product.getFats());
                            pstm.setFloat(3, product.getProteins());
                            pstm.setFloat(4, product.getCarbohydrates());
                            pstm.setFloat(5, product.getCalories());

                            pstm.executeUpdate();
                            connection.commit();
                            return true;
                        } catch (SQLException e) {
                            connection.rollback();
                            e.printStackTrace();
                        }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void eatProduct(List productsId, List amount, int userId, Date date) {
        String sql = "INSERT INTO user_products(user_id, products_id, date, amount) VALUES (?, ?, ?, ?)";
        try {
            connection.setAutoCommit(false);
            try(PreparedStatement pstm = connection.prepareStatement(sql)) {
                for(int i = 0; i < productsId.size(); i++) {
                    pstm.setInt(1, userId);
                    pstm.setInt(2, (Integer) productsId.get(i));
                    pstm.setDate(3, date);
                    pstm.setInt(4, (Integer) amount.get(i));
                    pstm.addBatch();
                }

                pstm.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<Product> findAllByDate(int currentPage, int recordsPerPage, int userId, Date date) {
        String sql = "SELECT user_id, products.id, name, calories, amount, date FROM products " +
                "JOIN user_products product ON products.id = product.products_id " +
                "JOIN user u3 ON product.user_id = u3.id WHERE user_id = ? AND date = ? LIMIT ?, ?";

        int start = currentPage * recordsPerPage - recordsPerPage;
        List<Product> foods = new ArrayList<>();

        try(PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, userId);
            pstm.setDate(2, date);
            pstm.setInt(3, start);
            pstm.setInt(4, recordsPerPage);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Float calories = rs.getFloat("calories");
                int amount  = rs.getInt("amount");
                foods.add(new Product(id, name, calories, amount));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return foods;
    }

    @Override
    public List<Product> findAllById(List productId) {
        String sql = "SELECT id,name,fats,proteins,carbohydrates,calories FROM products WHERE id = ?";
        List<Product> list = new ArrayList<>();

        try {
            connection.setAutoCommit(false);

            StringBuilder query = new StringBuilder();
            query.append(sql);
            int queryCount = productId.size();
            // If we have more than one parameter add a UNION clause:
            while (queryCount-- > 1) {
                query.append(" UNION ").append(sql);
            }
                try (PreparedStatement pstm = connection.prepareStatement(query.toString())) {
                    int paramIdx = 1;
                    for (Object aProductId : productId) {
                        pstm.setInt(paramIdx++, (Integer) aProductId);
                    }

                    ResultSet rs = pstm.executeQuery();
                    while (rs.next()) {
                        Product product = new Product();

                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        Float proteins = rs.getFloat("proteins");
                        Float carbohydrates = rs.getFloat("carbohydrates");
                        Float fats = rs.getFloat("fats");
                        Float calories = rs.getFloat("calories");
                        product.setId(id);
                        product.setName(name);
                        product.setProteins(proteins);
                        product.setCarbohydrates(carbohydrates);
                        product.setFats(fats);
                        product.setCalories(calories);
                        list.add(product);
                    }

                    connection.commit();

                } catch (SQLException e) {
                    connection.rollback();
                    e.printStackTrace();
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

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
            e.getMessage();
        }
        return foods;
    }

    @Override
    public int getNumberOfRows() {

        int numOfRows = 0;
         String sql = "SELECT COUNT(id) as id1 FROM products";
        try(PreparedStatement pstm = connection.prepareStatement(sql)) {

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                numOfRows = rs.getInt("id1");
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
            pstm.setFloat(5, product.getCalories());
            pstm.setInt(6, product.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {
        String sql = "SELECT name,fats,proteins,carbohydrates,calories FROM products WHERE id = ?";
        Product product = new Product();

        try(PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                Float proteins = rs.getFloat("proteins");
                Float carbohydrates = rs.getFloat("carbohydrates");
                Float fats = rs.getFloat("fats");
                Float calories = rs.getFloat("calories");
                product.setId(id);
                product.setName(name);
                product.setProteins(proteins);
                product.setCarbohydrates(carbohydrates);
                product.setFats(fats);
                product.setCalories(calories);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM products WHERE id = ? ";

        try(PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserProduct(int id) {
        String sql = "DELETE FROM user_products WHERE id = ?";

        try(PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, id);
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
