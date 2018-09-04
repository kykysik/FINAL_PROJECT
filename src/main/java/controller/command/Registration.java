package controller.command;

import controller.LocalController;
import model.entity.User;
import model.services.CaloriesNorm;

import javax.servlet.http.HttpServletRequest;

import java.sql.Date;
import java.time.LocalDate;


public class Registration implements CommandServlet {
    String error;
    String errorStr;
    User user;
    LocalController local = new LocalController();

    @Override
    public String execute(HttpServletRequest req) {
        CaloriesNorm caloriesNorm = new CaloriesNorm();
        error = null;
        errorStr = null;
        String gender = req.getParameter("gender");
        String login = req.getParameter("login"); ;
        String secondName = req.getParameter("secondName");
        String firstName = req.getParameter("firstName");
        String middleName = req.getParameter("middleName");
        String password = req.getParameter("password");
        String lang = req.getParameter("lang");

        if(login != null) {
            LocalDate birthDate = LocalDate.parse(req.getParameter("birthDate"));
            Float lifeActivity = Float.valueOf(req.getParameter("lifeActivity"));
            Float height = Float.valueOf(req.getParameter("height"));
            Float weight = Float.valueOf(req.getParameter("weight"));

            user = new User(secondName, firstName, middleName, login, password,
                    gender, birthDate, lifeActivity, height, weight);

             user = caloriesNorm.findNormCalories(user);
            System.out.println(user.getNormCalories());
            error = local.checkLangAndCorrectness(lang, user);
        }else {
            errorStr = "error";
        }

        req.setAttribute("user", user);
        req.setAttribute("error",error);
        if(error != null || errorStr != null) {
            return "/WEB-INF/regForm.jsp";
        }else return "/login";
    }
}
