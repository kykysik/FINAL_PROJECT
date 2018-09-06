package controller;

import controller.command.*;
import controller.command.home.*;
import controller.command.home.menu.portions.EditPortion;
import controller.command.home.menu.portions.PortionInfo;
import controller.command.home.menu.products.EditProduct;
import controller.command.home.menu.products.ProductInfo;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static view.TextConstant.RESP;

@WebServlet(urlPatterns = {"/"})

public class ServletController extends HttpServlet {
    private Map<String, CommandServlet> commands = new HashMap<>();
    private  static final String index = "/";
    private  static final String WEB = "WEB";
    private  static final String LOGGED = "loggedUsers";

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        servletConfig.getServletContext()
                .setAttribute(LOGGED, new HashSet<String>());

        commands.put("/logout", new LogOut());
        commands.put("/menu/product/edit", new EditProduct());
        commands.put("/menu/product/add", new ProductAdd());
        commands.put("/menu/portion/edit", new EditPortion());
        commands.put("/menu/portion/edit/product", new ProductInfo());
        commands.put("/", new StartPage());
        commands.put("/login", new LogIn());
        commands.put("/userInfo", new UserInfo());
        commands.put("/statistics", new StatisticsInfo());
        commands.put("/statistics/remove", new StatisticsRemove());
        commands.put("/menu", new Menu());
        commands.put("/menu/product", new ProductInfo());
        commands.put("/menu/portion", new PortionInfo());
        commands.put("/registration" , new Registration());

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute(RESP, resp);
        String path = req.getRequestURI();
        CommandServlet command = commands.getOrDefault(path ,
                r -> index);
        String page = command.execute(req);

        if(page.contains(WEB)){
            req.getRequestDispatcher(page).forward(req,resp);
        }else {
            resp.sendRedirect(req.getContextPath() + page);
        }
    }
}
