package controller.command.home.menu.portions;


import controller.command.CommandServlet;
import model.services.portion.PortionService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

import static view.TextConstant.*;

public class EditPortion implements CommandServlet {
    private static final String PORT_ID = "portId";
    private static final String PRODUCT_NAME = "productName";
    private static final String PRODUCT_ID = "productId";
    private static final String PRODUCT_CALORIES = "productCalories";
    private static final String MAP = "map";
    private static final String AMOUNT = "amount";
    @Override
    public String execute(HttpServletRequest request) {
        PortionService portionService = new PortionService();
        String id = request.getParameter(ID);
        String pId = request.getParameter(P_ID);
        String portId = request.getParameter(PORT_ID);

        if(portId != null && id != null) {
            id = id.replaceAll(REG_BRACKETS, EMPTY);
            portId = portId.replaceAll(REG_BRACKETS, EMPTY);
            int parseId = Integer.parseInt(id);
            int parsePortId = Integer.parseInt(portId);
            portionService.deleteById(parsePortId, parseId);
            return REDIRECT_PORTION;
        }

        if(pId != null) {
            Map map = portionService.find(Integer.parseInt(pId));
            request.setAttribute(MAP,map);
            ArrayList productCalories = (ArrayList) map.get(PRODUCT_CALORIES);
            request.setAttribute(PRODUCT_CALORIES,productCalories);
            ArrayList productName = (ArrayList) map.get(PRODUCT_NAME);
            request.setAttribute(PRODUCT_NAME,productName);
            ArrayList amount = (ArrayList) map.get(AMOUNT);
            request.setAttribute(AMOUNT, amount);
            ArrayList productId = (ArrayList) map.get(PRODUCT_ID);
            request.setAttribute(PRODUCT_ID, productId);

            return FORWARD_EDIT_PORTION;
        }
        return REDIRECT_PRODUCT;
    }
}
