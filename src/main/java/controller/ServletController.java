package controller;

import controller.command.*;
import model.entity.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/"})

public class ServletController extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();
    private String index = "/startPage";

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

        commands.put("logout", new LogOut());
        commands.put("startPage", new StartPage());
        commands.put("login", new LogIn());
        commands.put("userInfo", new UserInfo());
        commands.put("statistics", new Statistics());
        commands.put("menu", new Menu());
        commands.put("product", new ProductInfo());
        commands.put("portion", new PortionInfo());
        commands.put("exception" , new ExceptionCommand());
        commands.put("registration" , new Registration());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getRequestURI().replaceAll(".*/home/" , "");

        Command command = commands.getOrDefault(path ,
                (r)->index);
        String page = command.execute(req);
        if(page.contains("WEB")){
            req.getRequestDispatcher(page).forward(req,resp);
        }else {
            resp.sendRedirect(req.getContextPath() + page);
        }

    }
}
