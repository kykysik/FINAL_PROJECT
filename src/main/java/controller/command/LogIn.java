package controller.command;

import model.entity.User;
import model.services.UserService;
import model.utils.UserUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogIn implements Command{

    @Override
    public String execute(HttpServletRequest request) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserService userService = new UserService();
        User user ;
        String errorString = null;
        Boolean hasError = false;


        if( login == null || login.equals("") || password  == null || password.equals("")  ){
            return "/WEB-INF/login.jsp";
        }else {
            user = userService.find(login,password);
            HttpSession session = request.getSession();

            if (user == null) {
                //hasError = true;
                errorString = "User Name or password invalid";
            }

            if(CommandUtility.checkUserInContext(request, login)){
            CommandUtility.setUserRole(request, User.ROLE.UNKNOWN);
                return "/startPage"; // Кинуть на страницу логин с сообщением, что данный пользователь уже в системе.
            }

            if(login.equals("admin")) {
                CommandUtility.storeLoginedUser(session,user);

                CommandUtility.setUserRole(request, User.ROLE.ADMIN, login);
                return "/userInfo";
            } else {
                CommandUtility.storeLoginedUser(session,user);

                CommandUtility.setUserRole(request, User.ROLE.USER, login);
                return "/userInfo";
            }

        }


    }

}