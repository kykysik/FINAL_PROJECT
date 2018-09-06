package controller.command.home;


import controller.LocalController;
import controller.command.CommandServlet;
import controller.command.CommandUtility;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;

import static view.TextConstant.*;


public class StartPage implements CommandServlet {

    private LocalController local = new LocalController();
    private String errorString = null;
    @Override
    public String execute(HttpServletRequest request) {
        User user = CommandUtility.getLoginedUser(request.getSession());
        request.setAttribute(USER, user);
        String lang = request.getParameter(LANG);

        errorString = local.checkLangAndCorrectness(lang, user);
        request.setAttribute(ERROR_STRING, errorString);

        return START_PAGE;
    }
}