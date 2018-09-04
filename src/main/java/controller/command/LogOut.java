package controller.command;

import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOut implements CommandServlet {
    @Override
    public String execute(HttpServletRequest request) {
        HttpServletResponse response = (HttpServletResponse) request.getAttribute("resp");
        HttpSession session = request.getSession();

        User user =  CommandUtility.getLoginedUser(session);
        CommandUtility.setUserRole(request, User.ROLE.UNKNOWN, user.getLogin());
        CommandUtility.deleteUserCookie(response);
        session.invalidate();

        request.setAttribute("user", user);
        // Redirect (Перенаправить) к странице login.
        if(user == null){
            return  "/login";
        }else {
            return "/";
        }

    }//f5
    //f5
}