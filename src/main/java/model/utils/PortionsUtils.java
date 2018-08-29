package model.utils;

import model.dao.PortionsDao;
import model.entity.Portions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PortionsUtils implements PortionsDao {

    Connection connection;

    public PortionsUtils(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Portions entity) {
        String sql = "INSERT INTO portions(name, calories) VALUES (?, ?)";
        try(PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, entity.getName());
            pstm.setFloat(2, entity.getCalories());

            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Portions> findAll() {
        return null;
    }

    @Override
    public List<Portions> findAllPortions(int currentPage, int recordsPerPage) {
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
    public int getNumberOfRowsPortions() {

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
    public void delete(Portions portions) {
        String sql = "DELETE FROM portions WHERE id = ? ";

        try(PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, portions.getId());
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
