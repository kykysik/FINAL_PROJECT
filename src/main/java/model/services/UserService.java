package model.services;


import model.dao.UserDaoFactory;
import model.dao.UserDao;
import model.entity.User;

import java.util.List;

public class UserService {

    UserDaoFactory daoFactory = UserDaoFactory.getInstance();

    public List<String> findByUserName(User user) {
        try (UserDao dao = daoFactory.findByUserName()) {
            return dao.findByUserName(user);
        }
    }

    public boolean create(User user) {
        try (UserDao dao = daoFactory.create()) {
            return dao.create(user);
        }
    }

    public User find(String login, String password) {
        try (UserDao dao = daoFactory.find()) {
            return dao.find(login, password);
        }
    }

    public User findForCookie(String name) {
        try (UserDao dao = daoFactory.findForCookie()) {
            return dao.findForCookie(name);
        }
    }


}
