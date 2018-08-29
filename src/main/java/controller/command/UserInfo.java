package controller.command;

import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserInfo implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = CommandUtility.getLoginedUser(session);
        if(user == null){

            return "/login";
        }
        session.setAttribute("user",user);

        return "/WEB-INF/userInfo.jsp";
    }
}