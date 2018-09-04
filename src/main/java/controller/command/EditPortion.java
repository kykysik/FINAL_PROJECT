package controller.command;


import model.services.PortionService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EditPortion implements CommandServlet {
    private List editPortion = new ArrayList();
    @Override
    public String execute(HttpServletRequest request) {
        PortionService portionService = new PortionService();
        String id = request.getParameter("id");
        String pId = request.getParameter("pId");
        String portId = request.getParameter("portId");

        if(portId != null && id != null) {
            id = id.replaceAll("\\p{P}", "");
            portId = portId.replaceAll("\\p{P}", "");
            int parseId = Integer.parseInt(id);
            int parsePortId = Integer.parseInt(portId);
            portionService.deleteById(parsePortId, parseId);
            return "/menu/portion";
        }
        if(pId != null) {

            Map map = portionService.find(Integer.parseInt(pId));
            request.setAttribute("map",map);
            ArrayList productCalories = (ArrayList) map.get("productCalories");
            request.setAttribute("productCalories",productCalories);
            ArrayList productName = (ArrayList) map.get("productName");
            request.setAttribute("productName",productName);
            ArrayList amount = (ArrayList) map.get("amount");
            request.setAttribute("amount", amount);
            ArrayList productId = (ArrayList) map.get("productId");
            request.setAttribute("productId", productId);

            return "/WEB-INF/editPortion.jsp";
        }

      /*  float fats = Float.valueOf(request.getParameter("fats"));
        float proteins = Float.valueOf(request.getParameter("proteins"));
        float carbohydrates = Float.valueOf(request.getParameter("carbohydrates"));

        float calories = (float) ((proteins*4.1) + (carbohydrates*4.1) + (fats * 8.8));
        editPortion.setCalories(calories);
        editPortion.setFats(fats);
        editPortion.setCarbohydrates(carbohydrates);
        editPortion.setProteins(proteins);
        portionService.update(editProduct);*/

        return "/menu/product";

    }
}
