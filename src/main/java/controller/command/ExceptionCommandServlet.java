package controller.command;

import javax.servlet.http.HttpServletRequest;

public class ExceptionCommandServlet implements CommandServlet {
    @Override
    public String execute(HttpServletRequest request) {
        throw new RuntimeException("Generated exception");
    }
}