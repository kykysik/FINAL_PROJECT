package controller.listenner;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

@WebListener(value = "/*")

public class SessionListener implements HttpSessionListener {
    private int i = 0;
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("OOOOOOOOOOOOOOOOOOO: " + ++i);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute("loggedUsers");
        String userName = (String) httpSessionEvent.getSession().getServletContext()
                .getAttribute("userName");
        System.out.println("NAAAAAAMEEEEEEEEEEEEEE"+userName);
        System.out.println("NAAAAAAMEEEEEEEEEEEEEE"+loggedUsers);
        loggedUsers.remove(userName);
        System.out.println("NAAAAAAMEEEEEEEEEEEEEE"+loggedUsers);

        httpSessionEvent.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
    }
}