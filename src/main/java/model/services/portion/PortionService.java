package model.services.portion;

import model.dao.impl.portion.PortionDaoFactory;
import model.dao.impl.portion.PortionsDao;
import model.entity.Portions;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class PortionService {

    PortionDaoFactory daoFactory = PortionDaoFactory.getInstance();

    public List<Portions> findAllPortions(int currentPage, int recordsPerPage){
        try (PortionsDao dao = daoFactory.findAll()) {
            return dao.findAll(currentPage,recordsPerPage);
        }
    }

    public int getNumberOfRowsPortions() {
        try(PortionsDao dao = daoFactory.getNumberOfRows()) {
            return dao.getNumberOfRows();
        }
    }

    public void delete(int id) {
        try(PortionsDao dao = daoFactory.delete()) {
            dao.delete(id);
        }
    }

    public Map find(int id) {
        try(PortionsDao dao = daoFactory.find()) {
            return dao.find(id);
        }
    }

    public void editPortion(List count, List id, int portionId) {
        try(PortionsDao dao = daoFactory.editPortion()) {
             dao.editPortion(count,id, portionId);
        }
    }

    public void deleteById(int portionId, int productId) {
        try(PortionsDao dao = daoFactory.deleteById()) {
            dao.deleteById(portionId, productId);
        }
    }

    public void deleteUserPortion(int id) {
        try(PortionsDao dao = daoFactory.deleteUserPortion()) {
            dao.deleteUserPortion(id);
        }
    }

    public void createPortionProduct(List product, List amount, int portionId) {
        try(PortionsDao dao = daoFactory.createPortionProduct()) {
            dao.createPortionProduct(product, amount, portionId);
        }
    }

    public boolean create(Portions entity) {
        try(PortionsDao dao = daoFactory.create()) {
            return dao.create(entity);
        }
    }

    public Portions findPortion(Portions portions) {
        try(PortionsDao dao = daoFactory.findPortion()) {
            return dao.findPortion(portions);
        }
    }

    public void eatPortion(List portionsId, List amount, int userId, Date date) {
        try(PortionsDao dao = daoFactory.eatPortion()) {
            dao.eatPortion(portionsId, amount, userId, date);
        }
    }

    public List<Portions> findAllByDate(int currentPage, int recordsPerPage, int userId, Date date) {
        try(PortionsDao dao = daoFactory.findAllByDate()) {
            return dao.findAllByDate(currentPage, recordsPerPage, userId, date);
        }
    }

}
