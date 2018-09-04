package controller.command.locale;

import controller.command.Command;
import model.entity.User;

public interface CommandLang extends Command{
    String execute(User user);
}
