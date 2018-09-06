package controller.command.home.menu.products;

import controller.command.CommandServlet;
import model.entity.Product;
import model.services.product.ProductService;

import javax.servlet.http.HttpServletRequest;

import static view.TextConstant.*;

public class EditProduct implements CommandServlet {
    private static final String FATS = "fats";
    private static final String CARBOHYDRATES = "carbohydrates";
    private static final String PROTEINS = "proteins";
    private ProductService productService = new ProductService();
    private Product editProduct = new Product();
    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter(ID);

        if(id != null) {
            Product product = productService.find(Integer.parseInt(id));
            request.setAttribute(PRODUCT,product);
            editProduct.setId(Integer.parseInt(id));
            editProduct.setName(product.getName());
            return FORWARD_EDIT_PRODUCT;
        }

            float fats = Float.valueOf(request.getParameter(FATS));
            float proteins = Float.valueOf(request.getParameter(PROTEINS));
            float carbohydrates = Float.valueOf(request.getParameter(CARBOHYDRATES));
            Float calories = (proteins*4) + (carbohydrates*4) + (fats * 9);
            editProduct.setCalories(calories);
            editProduct.setFats(fats);
            editProduct.setCarbohydrates(carbohydrates);
            editProduct.setProteins(proteins);
            productService.update(editProduct);

        return REDIRECT_PRODUCT;
    }
}
