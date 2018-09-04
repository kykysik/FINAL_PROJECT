package model.utils;

import model.dao.PortionsDao;
import model.entity.Portions;
import model.entity.Product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PortionsUtils implements PortionsDao {

    Connection connection;

    public PortionsUtils(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Portions entity) {
        String preSql = "SELECT COUNT(1) AS count FROM portions WHERE name = ?";
        try(PreparedStatement pstm = connection.prepareStatement(preSql)) {
            connection.setAutoCommit(false);

            pstm.setString(1, entity.getName());

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                if(count == 0) {
                    String sql = " INSERT INTO portions(name, calories) VALUES (?, ?)";
                    try(PreparedStatement pstm1 = connection.prepareStatement(sql)) {
                        pstm1.setString(1, entity.getName());
                        pstm1.setFloat(2, entity.getCalories());
                        pstm1.executeUpdate();
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
    public void createPortionProduct(List product, List amount, int portionId) {
        String sql = "INSERT INTO portions_products(portions_id, products_id, amount) VALUES (?, ?, ?)";

        try {
            connection.setAutoCommit(false);
                try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                    for(int i = 0; i < product.size(); i++) {
                        Product prod = (Product) product.get(i);
                        pstm.setInt(1, portionId);
                        pstm.setInt(2, prod.getId());
                        pstm.setInt(3, (Integer) amount.get(i));
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
    public Portions findPortion(Portions portions) {
        String sql = "SELECT id FROM portions WHERE name = ?";
            try(PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, portions.getName());
                ResultSet rs = pstm.executeQuery();
                if (rs.next()) {
                    int portionId = rs.getInt("id");
                    portions.setId(portionId);

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
    return portions;
    }

    @Override
    public List<Portions> findAll(int currentPage, int recordsPerPage) {
        String sql = "SELECT id, name, calories FROM portions LIMIT ?, ?";
        int start = currentPage * recordsPerPage - recordsPerPage;
        List<Portions> list = new ArrayList<>();

        try(PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, start);
            pstm.setInt(2, recordsPerPage);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Float calories = rs.getFloat("calories");
                list.add(new Portions(id, name, calories));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Portions> findAllByDate(int currentPage, int recordsPerPage, int userId, Date date) {
        String sql = "SELECT user_id, portions.id, name, calories, amount, date " +
                "FROM portions JOIN user_portions u ON portions.id = u.portions_id " +
                "JOIN user u2 ON u.user_id = u2.id WHERE user_id = ? AND date = ? LIMIT ?, ?";

        int start = currentPage * recordsPerPage - recordsPerPage;
        List<Portions> foods = new ArrayList<>();

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
                foods.add(new Portions(id, name, calories, amount));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return foods;
    }

    @Override
    public Map find(int id) {
        String sql = "SELECT po.id, po.name, po.calories,pr.id as prId, pr.name as prName, pr.fats, pr.calories as productCalories," +
                "pr.proteins, pr.carbohydrates, product.amount " +
                "FROM portions po " +
                "JOIN portions_products product ON po.id = product.portions_id " +
                "JOIN products pr ON product.products_id = pr.id " +
                "WHERE po.id = ?";

        Map<String, List> map = new HashMap<>();
        List<Integer> poId = new ArrayList<Integer>();
        List<Integer> prId = new ArrayList<Integer>();
        List<String> prName = new ArrayList<String>();
        List<Float> proteins = new ArrayList<Float>();
        List<Float> carbohydrates = new ArrayList<Float>();
        List<Float> fats = new ArrayList<Float>();
        List<Float> prCalories = new ArrayList<Float>();
        List<Integer> ppAmount = new ArrayList<Integer>();

        try(PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, id);

            poId.add(id);
            map.put("id", poId);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String portionsName = rs.getString("name");
                Integer productId = rs.getInt("prId");
                Float portionCalories = rs.getFloat("calories");
                String productsName = rs.getString("prName");
                Float productsProteins = rs.getFloat("proteins");
                Float productsCarbohydrates = rs.getFloat("carbohydrates");
                Float productsFats = rs.getFloat("fats");
                Float productsCalories= rs.getFloat("productCalories");
                Integer Amount= rs.getInt("amount");
                prId.add(productId);
                prName.add(productsName);
                proteins.add(productsProteins);
                carbohydrates.add(productsCarbohydrates);
                fats.add(productsFats);
                prCalories.add(productsCalories);
                ppAmount.add(Amount);
                map.put("amount", ppAmount);
                map.put("calories", Collections.singletonList(portionCalories));
                map.put("name", Collections.singletonList(portionsName));
                map.put("productId", prId);
                map.put("productName", prName);
                map.put("proteins", proteins);
                map.put("carbohydrates", carbohydrates);
                map.put("fats", fats);
                map.put("productCalories", prCalories);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public int getNumberOfRows() {

        int numOfRows = 0;
        String sql = "SELECT COUNT(id) as id FROM portions";

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
    public void eatPortion(List portionsId, List amount, int userId, Date date) {
        String sql = "INSERT INTO user_portions(user_id, portions_id, amount, date) VALUES (?, ?, ?, ?)";
        try {
            connection.setAutoCommit(false);
            try(PreparedStatement pstm = connection.prepareStatement(sql)) {
                for(int i = 0; i < portionsId.size(); i++) {
                    pstm.setInt(1, userId);
                    pstm.setInt(2, (Integer) portionsId.get(i));
                    pstm.setInt(3, (Integer) amount.get(i));
                    pstm.setDate(4, date);
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
    public void editPortion(List count, List id, int portionId) {
        String sql = "INSERT INTO portions_products SET portions_id = ?, products_id = ?, amount = ?";
        try {
            connection.setAutoCommit(false);
                try(PreparedStatement pstm = connection.prepareStatement(sql)) {
                    for(int i = 0; i < id.size(); i++) {
                        pstm.setInt(1, portionId);
                        pstm.setInt(2, (Integer) id.get(i));
                        pstm.setInt(3, (Integer) count.get(i));
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
    public void update(Portions portions) {
        String sql = "UPDATE portions SET name = ?, calories = ? WHERE  id = ?";
        try(PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, portions.getName());
            pstm.setFloat(2, portions.getCalories());
            pstm.setInt(3, portions.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int portionId, int productId) {
        String sql = "DELETE FROM portions_products WHERE portions_id = ? AND products_id = ?";

        try(PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, portionId);
            pstm.setInt(2, productId);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserPortion(int id) {
        String sql = "DELETE FROM user_portions WHERE id = ?";

        try(PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, id);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM portions WHERE id = ? ";

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
