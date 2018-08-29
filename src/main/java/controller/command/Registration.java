package controller.command;

import model.entity.User;
import model.services.CheckOnCorrectness;

import javax.servlet.http.HttpServletRequest;

import java.sql.Date;

import java.util.Locale;
import java.util.ResourceBundle;

import static controller.RegExContainer.*;
import static controller.RegExContainer.REGEX_LOGIN_UA;
import static controller.RegExContainer.REGEX_NAME_UA;
import static view.TextConstant.*;

public class Registration implements Command {
    String error;
    User user;

    @Override
    public String execute(HttpServletRequest req) {
        error = null;
        if(req.getParameter("lang") == null) { // УБРАТЬ ЭТО
            return "/WEB-INF/regForm.jsp";
        }
        String temp;
        String login = req.getParameter("login"); ;
        String secondName = req.getParameter("secondName");
        String firstName = req.getParameter("firstName");
        String middleName = req.getParameter("middleName");
        String password = req.getParameter("password");
        String lang = req.getParameter("lang");
        String gender = req.getParameter("gender");
        Date  birthDate = Date.valueOf(req.getParameter("birthDate"));
        Float lifeActivity = Float.valueOf(req.getParameter("lifeActivity"));
        Float height = Float.valueOf(req.getParameter("height"));
        Float weight = Float.valueOf(req.getParameter("weight"));


        user = new User(secondName, firstName, middleName, login, password,
                gender, birthDate, lifeActivity, height, weight);

        CheckOnCorrectness check = new CheckOnCorrectness(user);

        ResourceBundle bundle = ResourceBundle.getBundle(
                MESSAGES_BUNDLE_NAME, new Locale(lang));
        switch (lang) { // В КОММАНД!
            case EN:
                if ((temp = check.checkInputData(REGEX_NAME_ENG, REGEX_LOGIN_ENG, bundle)) != null) {
                    error = temp;
                }
                break;
            case RU:
                if ((temp = check.checkInputData(REGEX_NAME_RUS, REGEX_LOGIN_RUS, bundle)) != null) {
                    error = temp;
                }
                break;
            case UA:
                if ((temp = check.checkInputData(REGEX_NAME_UA, REGEX_LOGIN_UA, bundle)) != null) {
                    error = temp;
                }
                break;
        }

        req.setAttribute("user", user);
        req.setAttribute("error",error);
        if(error != null) {
            return "/WEB-INF/regForm.jsp";

        }else return "/login";
    }
}
