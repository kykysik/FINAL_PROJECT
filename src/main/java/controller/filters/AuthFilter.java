package controller.filters;

import controller.command.CommandUtility;
import model.dao.JDBCPortionDaoFactory;
import model.entity.User;
import model.services.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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
        ServletContext context = request.getServletContext();
        User user = (User)session.getAttribute("user");
        System.out.println("_________________________________________________");
        if(user == null ) {
            CommandUtility.setUserRole(req, User.ROLE.UNKNOWN);
        }else{
            if(req.getServletPath().equals("/login")){
                req.getRequestDispatcher("/WEB-INF/userInfo.jsp").forward(req,res);            }
        }

        System.out.println(session.getAttribute("role"));
        System.out.println(context.getAttribute("loggedUsers"));
        System.out.println("_________________________________________________");



        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}