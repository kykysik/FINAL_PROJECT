package controller;

import model.entity.User;
import model.services.CheckOnCorrectness;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

import static controller.RegExContainer.*;
import static controller.RegExContainer.REGEX_LOGIN_UA;
import static controller.RegExContainer.REGEX_NAME_UA;
import static view.TextConstant.*;

public class LocalController {

   /* public void Local() {
        CheckOnCorrectness check = new CheckOnCorrectness(user);

        ResourceBundle bundle = ResourceBundle.getBundle(
                MESSAGES_BUNDLE_NAME, new Locale(lang));
        switch (lang) {
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
    }*/


}
