package controller.command;


import javax.servlet.http.HttpServletRequest;

public class StartPage implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/startPage.jsp";
    }
}