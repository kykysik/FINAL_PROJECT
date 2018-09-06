package controller.command.home;

import controller.LocalController;
import controller.ServletController;
import controller.command.CommandServlet;
import controller.command.CommandUtility;
import model.entity.User;
import model.services.user.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static view.TextConstant.*;


public class LogIn implements CommandServlet {
    private static final String REMEMBER = "rememberMe";
    private static final String Y = "Y";
    private static final String ADMIN = "admin";

    @Override
    public String execute(HttpServletRequest request) {
        HttpServletResponse response  = (HttpServletResponse) request.getAttribute(RESP);
        LocalController local = new LocalController();
        String login = request.getParameter(LOGIN);
        String lang = request.getParameter(LANG);
        String password = request.getParameter(PASSWORD);
        String rememberMeStr = request.getParameter(REMEMBER);
        boolean remember = Y.equals(rememberMeStr);
        UserService userService = new UserService();
        User user = userService.find(login,password);
        String errorString = null;
        boolean hasError;
        HttpSession session = request.getSession();
            if( login == null || login.equals(EMPTY) || password  == null || password.equals(EMPTY)  ){
                return FORWARD_LOGIN;
            }
        errorString = local.checkLangAndCorrectness(lang, user);


        if (user == null) {
            hasError = true;
            errorString = LOGIN_ERROR;
        }else hasError = false;


        session.setAttribute(USER, user);

            if (hasError) {
                user = new User();
                user.setLogin(login);
                user.setPassword(password);
                request.setAttribute(ERROR_STRING, errorString);
                request.setAttribute(USER, user);
                return FORWARD_LOGIN;

            }else {
                request.setAttribute(ERROR_STRING, errorString);
                if (remember) {
                    CommandUtility.storeUserCookie(response, user);
                }
                else {
                    CommandUtility.deleteUserCookie(response);
                }
                if(login.equals(ADMIN)) {
                    if(CommandUtility.checkUserInContext(request, login)){
                        return START;
                    }

                    CommandUtility.storeLoginedUser(session,user);
                    CommandUtility.setUserRole(request, User.ROLE.ADMIN, login);
                    return REDIRECT_INFO;
                }else {
                    if(CommandUtility.checkUserInContext(request, login)){
                        return START;
                    }

                    CommandUtility.storeLoginedUser(session,user);
                    CommandUtility.setUserRole(request, User.ROLE.USER, login);
                    return REDIRECT_INFO;
                }
            }
    }
}