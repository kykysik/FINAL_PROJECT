package controller.command.locale;

import model.entity.User;
import model.services.CheckOnCorrectness;

import java.util.Locale;
import java.util.ResourceBundle;

import static controller.RegExContainer.REGEX_LOGIN_UA;
import static controller.RegExContainer.REGEX_NAME_UA;
import static view.TextConstant.MESSAGES_BUNDLE_NAME;
import static view.TextConstant.UA;

public class LocalUA implements CommandLang {

    private String temp = null;

    @Override
    public String execute(User user) {
        ResourceBundle bundle = ResourceBundle.getBundle(
                MESSAGES_BUNDLE_NAME, new Locale(UA));

        CheckOnCorrectness check = new CheckOnCorrectness(user);

        if ((temp = check.checkInputData(REGEX_NAME_UA, REGEX_LOGIN_UA, bundle)) != null) {
            return temp;
        }
        return temp;
    }
}
