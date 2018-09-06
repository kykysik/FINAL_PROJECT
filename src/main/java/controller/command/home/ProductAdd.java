package controller.command.home;

import controller.command.CommandServlet;
import model.entity.Product;
import model.services.product.ProductService;

import javax.servlet.http.HttpServletRequest;

import static view.TextConstant.*;

public class ProductAdd implements CommandServlet {
    private static final String PRODUCT_EXIST =  "this product is exist";
    private static final String PRODUCT_ADD = "/menu/product/add";
    private static final String FATS = "fats";
    private static final String CARBOHYDRATES = "carbohydrates";
    private static final String PROTEINS = "proteins";
    private ProductService productService = new ProductService();
    private String error = null;

    @Override
    public String execute(HttpServletRequest request) {
        String addName = request.getParameter(NAME_PRODUCT);

        if(request.getRequestURI().equals(PRODUCT_ADD)) {
            if(addName != null) {
                Float addFats = Float.valueOf(request.getParameter(FATS));
                Float addCarbohydrates = Float.valueOf(request.getParameter(CARBOHYDRATES));
                Float addProteins = Float.valueOf(request.getParameter(PROTEINS));
                Float addCalories = (addProteins*4) + (addCarbohydrates*4) + (addFats * 9);
                Product product = new Product();
                product.setName(addName);
                product.setFats(addFats);
                product.setCarbohydrates(addCarbohydrates);
                product.setProteins(addProteins);
                product.setCalories(addCalories);
                boolean state = productService.create(product);
                if(state){
                    return REDIRECT_PRODUCT;
                }else {
                    error = PRODUCT_EXIST;
                    request.setAttribute(ERROR, error);
                    return FORWARD_NEW_PRODUCT;
                }
            }
        }
        return FORWARD_NEW_PRODUCT;
    }
}
