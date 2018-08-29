package model.dao;

import model.entity.Portions;

import java.util.List;

public interface PortionsDao extends GenericDao<Portions>  {
     List<Portions> findAllPortions(int currentPage, int recordsPerPage);
     int getNumberOfRowsPortions();

}
