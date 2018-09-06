package model.dao.impl.product;

import model.dao.GenericDao;
import model.entity.Product;

import java.sql.Date;
import java.util.List;

public interface ProductsDao extends GenericDao<Product> {
     Product find(int id);
     int getNumberOfRows();
     List<Product> findAll(int currentPage, int recordsPerPage);
     List<Product> findAllById(List productId);
     void eatProduct(List productsId, List amount, int userId, Date date);
     List<Product> findAllByDate(int currentPage, int recordsPerPage, int userId, Date date);
     void deleteUserProduct(int id);
}
