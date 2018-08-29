package model.dao;

import model.entity.Product;

import java.util.List;

public interface ProductsDao extends GenericDao<Product> {
     int getNumberOfRows();
     List<Product> findAll(int currentPage, int recordsPerPage);

}
