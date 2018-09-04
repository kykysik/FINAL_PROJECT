package model.services;

import model.dao.ProductDaoFactory;
import model.dao.UserDaoFactory;
import model.dao.ProductsDao;
import model.entity.Product;

import java.sql.Date;
import java.util.List;

public class ProductService {
    ProductDaoFactory daoFactory = ProductDaoFactory.getInstance();
    public List<Product> findAll(int currentPage, int recordsPerPage) {
        try (ProductsDao dao = daoFactory.findAll()) {
            return dao.findAll(currentPage, recordsPerPage);
        }
    }

    public int getNumberOfRows() {
        try (ProductsDao dao = daoFactory.getNumberOfRows()) {
            return dao.getNumberOfRows();
        }
    }

    public void delete(int id) {
        try (ProductsDao dao = daoFactory.delete()) {
            dao.delete(id);
        }
    }

    public void deleteUserProduct(int id) {
        try (ProductsDao dao = daoFactory.deleteUserProduct()) {
            dao.deleteUserProduct(id);
        }
    }

    public Product find(int id) {
        try (ProductsDao dao = daoFactory.find()) {
            return dao.find(id);
        }
    }

    public void update(Product product) {
        try(ProductsDao dao = daoFactory.update()) {
             dao.update(product);
        }
    }

    public List<Product> findAllById(List productId) {
        try(ProductsDao dao = daoFactory.findAllById()) {
            return dao.findAllById(productId);
        }
    }

    public boolean create(Product product) {
        try(ProductsDao dao = daoFactory.create()) {
             return dao.create(product);
        }
    }

    public void eatProduct(List productsId, List amount, int userId, Date date) {
        try(ProductsDao dao = daoFactory.eatProduct()) {
              dao.eatProduct(productsId, amount, userId, date);
        }
    }
    public List<Product> findAllByDate(int currentPage, int recordsPerPage, int userId, Date date) {
        try(ProductsDao dao = daoFactory.findAllByDate()) {
            return dao.findAllByDate(currentPage, recordsPerPage, userId, date);
        }
    }

}
