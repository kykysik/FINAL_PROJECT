package controller.listenner;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

@WebListener(value = "/*")

public class SessionListener implements HttpSessionListener {
    private static final String LOGGED = "loggedUsers";
    private static final String NAME = "userName";

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute(LOGGED);
        String userName = (String) httpSessionEvent.getSession().getServletContext()
                .getAttribute(NAME);

        loggedUsers.remove(userName);

        httpSessionEvent.getSession().getServletContext().setAttribute(LOGGED, loggedUsers);
    }
}