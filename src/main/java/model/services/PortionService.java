package model.services;

import model.dao.DaoFactory;
import model.dao.PortionsDao;
import model.entity.Portions;

import java.util.List;

public class PortionService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Portions> findAllPortions(int currentPage, int recordsPerPage){
        try (PortionsDao dao = daoFactory.findAllPortions()) {
            return dao.findAllPortions(currentPage,recordsPerPage);
        }
    }

    public int getNumberOfRowsPortions() {
        try(PortionsDao dao = daoFactory.getNumberOfRowsPortions()) {
            return dao.getNumberOfRowsPortions();
        }
    }
}
