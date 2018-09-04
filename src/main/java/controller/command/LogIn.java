package controller.command;

import controller.LocalController;
import model.entity.User;
import model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LogIn implements CommandServlet {

    @Override
    public String execute(HttpServletRequest request) {
        HttpServletResponse response  = (HttpServletResponse) request.getAttribute("resp");
        LocalController local = new LocalController();
        String login = request.getParameter("login");
        String lang = request.getParameter("lang");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);

        UserService userService = new UserService();
        User user = userService.find(login,password);
        String errorString = null;
        boolean hasError;
        HttpSession session = request.getSession();
            if( login == null || login.equals("") || password  == null || password.equals("")  ){
                return "/WEB-INF/login.jsp";
            }
        errorString = local.checkLangAndCorrectness(lang, user);

        if (user == null) {
            hasError = true;
            errorString = "User Name or password invalid";
        }else hasError = false;


        session.setAttribute("user", user);

        //Ошибка при вводе
            if (hasError) {
                user = new User();
                user.setLogin(login);
                user.setPassword(password);
                request.setAttribute("errorString", errorString);
                request.setAttribute("user", user);
                return "/WEB-INF/login.jsp";

            }else {
                request.setAttribute("errorString", errorString);

                // Галочка запомнить юзера
                if (remember) {
                    CommandUtility.storeUserCookie(response, user);
                }
                // Наоборот, удалить Cookie
                else {
                    CommandUtility.deleteUserCookie(response);
                }
                if(login.equals("admin")) {
                    if(CommandUtility.checkUserInContext(request, login)){
                        return "/startPage";
                    }

                    CommandUtility.storeLoginedUser(session,user);
                    CommandUtility.setUserRole(request, User.ROLE.ADMIN, login);
                    return "/userInfo";
                }else { // Вытянуть логины со списка и если данный логин существует - дать роль
                    if(CommandUtility.checkUserInContext(request, login)){
                        return "/startPage";
                    }

                    CommandUtility.storeLoginedUser(session,user);
                    CommandUtility.setUserRole(request, User.ROLE.USER, login);
                    return "/userInfo";
                }


            }

    }
}