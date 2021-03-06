package controller.filters;

import controller.command.CommandUtility;
import model.entity.User;
import model.services.user.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static view.TextConstant.USER;

@WebFilter(filterName = "cookieFilter", urlPatterns = { "/*" })
public class CookieFilter implements Filter {
    private static final String COOKIE_CHECKED = "COOKIE_CHECKED";
    private static final String CHECKED = "CHECKED";
    private static final String ADMIN = "admin";

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        UserService userService = new UserService();
        User userInSession = CommandUtility.getLoginedUser(session);

        if (userInSession != null) {
            session.setAttribute(COOKIE_CHECKED, CHECKED);
            chain.doFilter(request, response);
            return;
        }

        // Флаг(flag) для проверки Cookie.
        String checked = (String) session.getAttribute(COOKIE_CHECKED);

        if (checked == null) {
            String userName = CommandUtility.getUserNameInCookie(req);
            User user = userService.findForCookie(userName);
            CommandUtility.storeLoginedUser(session, user);
            if(userName != null) {
                if(userName.equals(ADMIN)){
                    CommandUtility.checkUserInContext(req,userName);
                    CommandUtility.setUserRole(req, User.ROLE.ADMIN, userName);
                }else {
                    CommandUtility.checkUserInContext(req,userName);
                    CommandUtility.setUserRole(req, User.ROLE.USER, userName);
                }
                session.setAttribute(USER,user);
            }

            // Отметить проверенные Cookie.
            session.setAttribute(COOKIE_CHECKED, COOKIE_CHECKED);
        }

        chain.doFilter(request, response);
    }

}