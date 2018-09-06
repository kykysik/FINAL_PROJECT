package controller.command.home;

import controller.command.CommandServlet;
import controller.command.CommandUtility;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static view.TextConstant.*;

public class LogOut implements CommandServlet {
    @Override
    public String execute(HttpServletRequest request) {
        HttpServletResponse response = (HttpServletResponse) request.getAttribute(RESP);
        HttpSession session = request.getSession();

        User user =  CommandUtility.getLoginedUser(session);
        CommandUtility.setUserRole(request, User.ROLE.UNKNOWN, user.getLogin());
        CommandUtility.deleteUserCookie(response);
        session.invalidate();

        request.setAttribute(USER, user);
        if(user == null){
            return  REDIRECT_LOGIN;
        }else {
            return START;
        }
    }
}