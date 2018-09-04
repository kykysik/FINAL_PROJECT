package controller.command;

import javax.servlet.http.HttpServletRequest;

public interface CommandServlet extends Command {
    String execute(HttpServletRequest request);
}
