package model.dao.impl.portion;

import model.dao.GenericDao;
import model.entity.Portions;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface PortionsDao extends GenericDao<Portions> {
     int getNumberOfRows();
     List<Portions> findAll(int currentPage, int recordsPerPage);
     Map find(int id);
     void editPortion(List count, List id, int portionId);
     void deleteById(int portionId, int productId);
     void createPortionProduct(List product, List amount, int portionId);
     Portions findPortion(Portions portions);
     void eatPortion(List portionsId, List amount, int userId, Date date);
     List<Portions> findAllByDate(int currentPage, int recordsPerPage, int userId, Date date);
     void deleteUserPortion(int id);

}
