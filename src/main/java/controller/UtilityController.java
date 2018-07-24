package controller;

import model.User;
import model.Services;

import java.util.ResourceBundle;

import static view.TextConstant.WRONG_INPUT_DATA;


/**
 * Controller.
 */
 public class UtilityController {
     User user;

     Services services;

    public UtilityController(User user) {
         this.user = user;
    }

    public String checkInputData(String regName, String regLogin, ResourceBundle bundle){
         String error = bundle.getString(WRONG_INPUT_DATA);

        services = new Services(user, regName, regLogin);
        if ((isCorrectSecondName() || isCorrectFirstName()
        || isCorrectMiddleName() || isCorrectLogin())) {
            return error;
        }
        return null;
    }

    /**
     * this method is checked SecondName
     * @return true, if secondName is correct
     */
    private boolean isCorrectSecondName() {
        String res = user.getSecondName();
        if (services.checkSecondName(res)) {
            return false;
        }
        return true;
    }

    /**
     * This method is checked FirstName
     * @return true, if firstName is correct
     */
    private boolean isCorrectFirstName() {

        String res = user.getFirstName();
        if (services.checkFirstName(res)) {
            return false;
        }
        return true;
    }

    /**
     * This method is checked MiddleName
     * @return true, if middleName is correct
     */
    private boolean isCorrectMiddleName() {

        String res = user.getMiddleName();
        if (services.checkMiddleName(res)) {
            return false;
        }
        return true;
    }

    private boolean isCorrectLogin() {

        String res = user.getLogin();

        if (services.checkLogin(res)) {
            return false;
        }
        return true;
    }
}
