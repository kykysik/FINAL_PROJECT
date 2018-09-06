package controller.command.home;

import controller.command.CommandServlet;
import model.services.portion.PortionService;
import model.services.product.ProductService;

import javax.servlet.http.HttpServletRequest;

import static view.TextConstant.*;
import static view.TextConstant.TYPE;

public class StatisticsRemove implements CommandServlet {
    PortionService portionService = new PortionService();
    ProductService productService = new ProductService();

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter(ID);
        String type = request.getParameter(TYPE);

        if(type != null && type.equals(PORTION)) {
            portionService.deleteUserPortion(Integer.parseInt(id));
        }else if(type != null && type.equals(PRODUCT)) {
            productService.deleteUserProduct(Integer.parseInt(id));
        }

        return REDIRECT_STATISTICS;
    }
}
