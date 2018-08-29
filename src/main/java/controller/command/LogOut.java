package controller.command;

import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOut implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        User u = (User) request.getAttribute("user");


        User user =  CommandUtility.getLoginedUser(session);
        System.out.println("TEST: ");
        System.out.println(user.getLogin());
        CommandUtility.setUserRole(request, User.ROLE.UNKNOWN, user.getLogin());
        session.invalidate();

        request.setAttribute("user", user);
        // Redirect (Перенаправить) к странице login.
        if(user == null){
            return  "/login";
        }else {
            return "/WEB-INF/startPage";
        }

    }//f5
    //f5
}