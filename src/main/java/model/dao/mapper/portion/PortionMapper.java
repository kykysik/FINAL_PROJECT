package model.dao.mapper.portion;

import model.dao.mapper.ObjectMapper;
import model.entity.Portions;

import java.sql.ResultSet;
import java.sql.SQLException;


public interface PortionMapper extends ObjectMapper<Portions> {
    Portions extractFromResultId(ResultSet rs) throws SQLException ;
    Portions extractFromResultAmount(ResultSet rs) throws SQLException ;
 }
