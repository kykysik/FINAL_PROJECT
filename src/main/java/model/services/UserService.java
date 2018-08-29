package model.services;


import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

import java.util.List;

public class UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<String> findByUserName(User user){
        try (UserDao dao = daoFactory.findByUserName()) {
            return dao.findByUserName(user);
        }
    }

    public void create(User user) {
        try (UserDao dao = daoFactory.create()) {
            dao.create(user);
        }
    }

    public User find(String login, String password) {
        try (UserDao dao = daoFactory.find()) {
          return dao.find(login,password);
        }
    }
}
