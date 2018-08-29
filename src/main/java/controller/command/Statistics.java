package controller.command;

import javax.servlet.http.HttpServletRequest;

public class Statistics implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/statistics.jsp";
    }
}