package controller;

import controller.UtilityController;
import model.LoginException;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

import static controller.RegExContainer.*;
import static view.TextConstant.*;

@WebServlet(urlPatterns = {"/home"})

public class ServletController extends HttpServlet {
    CopyOnWriteArrayList<String> list;

    @Override
    public void init() {
        list = new CopyOnWriteArrayList();
        list.add("Bogdan");
        list.add("Богдан");
        list.add("Валера");
        list.add("Valera");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF8");
        String error = null;
        String temp;
        String login = req.getParameter("login");
        String secondName = req.getParameter("secondName");
        String firstName = req.getParameter("firstName");
        String middleName = req.getParameter("middleName");
        String password =  req.getParameter("password");
        String submit = req.getParameter("submit");
        String lang;

        if(submit.equals(tempEN)) {
            lang = EN;
        }else if(submit.equals(tempRU)) {
            lang = RU;
        }else {
            lang = UA;
        }

        User user = new User(secondName,firstName,middleName,login,password);
        UtilityController utilityController = new UtilityController(user);
        ResourceBundle bundle = ResourceBundle.getBundle(
                MESSAGES_BUNDLE_NAME,new Locale(lang));

        if(list.contains(login)){

            try {
                throw new LoginException();
            } catch (LoginException e) {
                error = bundle.getString(INFO);
                req.setAttribute("error",error);
            }
        }else {
            list.add(login);
        }

        switch (lang) {
            case EN:
               if((temp = utilityController.checkInputData(REGEX_NAME_ENG, REGEX_LOGIN_ENG, bundle)) != null) {
                   error = temp;
               }
                break;
            case RU:
               if((temp = utilityController.checkInputData(REGEX_NAME_RUS, REGEX_LOGIN_RUS, bundle)) != null) {
                    error = temp;
               }
                break;
            case UA:
                if((temp = utilityController.checkInputData(REGEX_NAME_UA, REGEX_LOGIN_UA, bundle)) != null) {
                    error = temp;
                }
                break;
        }
        req.setAttribute("user",user);

        if(error != null){
            req.setAttribute("error",error);

            RequestDispatcher dispatcher
                    = req.getRequestDispatcher("/WEB-INF/view/regForm.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/regForm.jsp");

        dispatcher.forward(req,resp);
    }
}
