package controller.command.home;

import controller.command.CommandServlet;
import controller.command.CommandUtility;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static view.TextConstant.REDIRECT_LOGIN;
import static view.TextConstant.USER;
import static view.TextConstant.USER_INFO;

public class UserInfo implements CommandServlet {
    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = CommandUtility.getLoginedUser(session);
        if(user == null){

            return REDIRECT_LOGIN;
        }
        session.setAttribute(USER,user);

        return USER_INFO;
    }
}