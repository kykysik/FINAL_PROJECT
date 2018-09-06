package model.services.user;

import model.LoginException;
import model.entity.User;

import java.util.List;
import java.util.ResourceBundle;

import static view.TextConstant.INFO;
import static view.TextConstant.WRONG_INPUT_DATA;

 public class CheckOnCorrectness {
     User user;
     String error = null;
     UserService utils = new UserService();
     CheckUser services;

    public CheckOnCorrectness(User user) {
         this.user = user;
    }

    public String checkInputData(String regName, String regLogin, ResourceBundle bundle) {

        List<String> list = utils.findByUserName(user);
        error = bundle.getString(WRONG_INPUT_DATA);
        services = new CheckUser(user, regName, regLogin);

        if ((isCorrectSecondName() || isCorrectFirstName()
        || isCorrectMiddleName() || isCorrectLogin())) {
            return error;

        } else if(list.contains(user.getLogin())){
                try {
                    throw new LoginException();
                } catch (LoginException e) {
                    e.getMessage();
                    e.getStackTrace();
                    error = bundle.getString(INFO);
                }
            return error;
        } else utils.create(user);

        return null;
    }

    /**
     * this method is checked SecondName
     * @return true, if secondName is correct
     */
    private boolean isCorrectSecondName() {
        String res = user.getSecondName();

       return !services.checkSecondName(res);
    }

    /**
     * This method is checked FirstName
     * @return true, if firstName is correct
     */
    private boolean isCorrectFirstName() {
        String res = user.getFirstName();

        return !services.checkFirstName(res);
    }

    /**
     * This method is checked MiddleName
     * @return true, if middleName is correct
     */
    private boolean isCorrectMiddleName() {
        String res = user.getMiddleName();

       return !services.checkMiddleName(res);
    }

    private boolean isCorrectLogin() {
        String res = user.getLogin();

        return !services.checkLogin(res);
    }
}
