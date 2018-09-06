package controller.command.home;

import controller.command.CommandServlet;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static view.TextConstant.FORWARD_MENU;

public class Menu implements CommandServlet {

    @Override
    public String execute(HttpServletRequest request) {
        return FORWARD_MENU;
    }
}