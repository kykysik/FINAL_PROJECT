package controller.command.locale;

import model.entity.User;
import model.services.CheckOnCorrectness;

import java.util.Locale;
import java.util.ResourceBundle;

import static controller.RegExContainer.REGEX_LOGIN_ENG;
import static controller.RegExContainer.REGEX_NAME_ENG;
import static view.TextConstant.EN;
import static view.TextConstant.MESSAGES_BUNDLE_NAME;

public class LocalEN implements CommandLang {

    private String temp = null;

    @Override
    public String execute(User user) {
        ResourceBundle bundle = ResourceBundle.getBundle(
                MESSAGES_BUNDLE_NAME, new Locale(EN));

        CheckOnCorrectness check = new CheckOnCorrectness(user);

        if ((temp = check.checkInputData(REGEX_NAME_ENG, REGEX_LOGIN_ENG, bundle)) != null) {
            return temp;
        }
        return temp;
    }
}
