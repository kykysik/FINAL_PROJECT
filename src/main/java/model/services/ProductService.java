package model.services;

import model.dao.DaoFactory;
import model.dao.GenericDao;
import model.dao.ProductsDao;
import model.dao.UserDao;
import model.entity.Product;
import model.entity.User;

import java.util.List;

public class ProductService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Product> findAll(int currentPage, int recordsPerPage){
        try (ProductsDao dao = daoFactory.findAll()) {
            return dao.findAll(currentPage,recordsPerPage);
        }
    }

    public int getNumberOfRows() {
        try(ProductsDao dao = daoFactory.getNumberOfRows()) {
            return dao.getNumberOfRows();
        }
    }

}
