package controller;

import controller.command.locale.CommandLang;
import controller.command.locale.LocalEN;
import controller.command.locale.LocalUA;
import model.entity.User;

import java.util.HashMap;
import java.util.Map;

import static view.TextConstant.*;

public class LocalController {

    private Map<String, CommandLang> commands = new HashMap<>();

    private String index = EN;

    public String checkLangAndCorrectness(String lang, User user) {

        commands.put(UA, new LocalUA());
        commands.put(EN, new LocalEN());

        CommandLang command = commands.getOrDefault(lang ,
                (r)->index);
        return command.execute(user);
    }

}
