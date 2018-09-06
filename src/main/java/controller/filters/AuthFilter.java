package controller.filters;

import controller.command.CommandUtility;
import model.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static view.TextConstant.*;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "/*" })
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute(USER);
        if(user == null ) {
            CommandUtility.setUserRole(req, User.ROLE.UNKNOWN);
        }else{
            if(req.getServletPath().equals(REDIRECT_LOGIN)
                    || req.getServletPath().equals(REDIRECT_REGISTRATION)){
                req.getRequestDispatcher(USER_INFO).forward(req,res);            }
        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}