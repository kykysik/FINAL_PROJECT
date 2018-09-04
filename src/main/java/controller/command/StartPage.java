package controller.command;


import controller.LocalController;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;

public class StartPage implements CommandServlet {
    LocalController local = new LocalController();
    String errorString = null;
    @Override
    public String execute(HttpServletRequest request) {
        User user = CommandUtility.getLoginedUser(request.getSession());
        request.setAttribute("user", user   );
        String lang = request.getParameter("lang");

        errorString = local.checkLangAndCorrectness(lang, user);
        request.setAttribute("errorString", errorString);

        return "/WEB-INF/startPage.jsp";
    }
}