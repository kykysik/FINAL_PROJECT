package model.dao.impl.user;

import model.dao.GenericDao;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserDao extends GenericDao<User> {

    List<String> findByUserName(User user);
    User find(String login, String password);
    User findForCookie(String name);
    int getNumberOfRows();
    List<User> findAll(int currentPage, int recordsPerPage);

}
