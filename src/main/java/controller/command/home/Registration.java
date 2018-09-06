package controller.command.home;

import controller.LocalController;
import controller.command.CommandServlet;
import model.entity.User;
import model.services.user.CaloriesNorm;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDate;

import static view.TextConstant.*;


public class Registration implements CommandServlet {

    private String error;
    private String errorStr;
    private User user;
    private LocalController local = new LocalController();

    @Override
    public String execute(HttpServletRequest req) {
        CaloriesNorm caloriesNorm = new CaloriesNorm();
        error = null;
        errorStr = null;
        String gender = req.getParameter(GENDER);
        String login = req.getParameter(LOGIN);
        String secondName = req.getParameter(SECOND_NAME);
        String firstName = req.getParameter(FIRST_NAME);
        String middleName = req.getParameter(MIDDLE_NAME);
        String password = req.getParameter(PASSWORD);
        String lang = req.getParameter(LANG);

        if(login != null) {
            LocalDate birthDate = LocalDate.parse(req.getParameter(BIRTH));
            Float lifeActivity = Float.valueOf(req.getParameter(ACTIVITY));
            Float height = Float.valueOf(req.getParameter(HEIGHT));
            Float weight = Float.valueOf(req.getParameter(WEIGHT));

            user = new User(secondName, firstName, middleName, login, password,
                    gender, birthDate, lifeActivity, height, weight);

             user = caloriesNorm.findNormCalories(user);
            System.out.println(user.getNormCalories());
            error = local.checkLangAndCorrectness(lang, user);
        }else {
            errorStr = ERROR;
        }

        req.setAttribute(USER, user);
        req.setAttribute(ERROR,error);
        if(error != null || errorStr != null) {
            return FORWARD_REG_FORM;
        }else return REDIRECT_LOGIN;
    }
}
