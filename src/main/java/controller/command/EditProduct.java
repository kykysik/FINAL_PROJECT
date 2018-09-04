package controller.command;

import model.entity.Product;
import model.services.ProductService;

import javax.servlet.http.HttpServletRequest;

public class EditProduct implements CommandServlet {
    ProductService productService = new ProductService();
    Product editProduct = new Product();
    String error = null;
    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        String addName = request.getParameter("nameProduct");


        if(request.getRequestURI().equals("/menu/product/add")) {
            if(addName != null) {
                Float addFats = Float.valueOf(request.getParameter("fats"));
                Float addCarbohydrates = Float.valueOf(request.getParameter("carbohydrates"));
                Float addProteins = Float.valueOf(request.getParameter("proteins"));
                Float addCalories = (addProteins*4) + (addCarbohydrates*4) + (addFats * 9);
                Product product = new Product();
                product.setName(addName);
                product.setFats(addFats);
                product.setCarbohydrates(addCarbohydrates);
                product.setProteins(addProteins);
                product.setCalories(addCalories);
                boolean state = productService.create(product);
                if(state){
                    return "/menu/product";
                }else {
                    error = "this product is exist";
                    request.setAttribute("error", error);
                    return "/WEB-INF/newProduct.jsp";
                }
            }
            return "/WEB-INF/newProduct.jsp";
        }

        if(id != null) {

            Product product = productService.find(Integer.parseInt(id));
            request.setAttribute("product",product);
            editProduct.setId(Integer.parseInt(id));
            editProduct.setName(product.getName());
            return "/WEB-INF/editProduct.jsp";
        }

            float fats = Float.valueOf(request.getParameter("fats"));
            float proteins = Float.valueOf(request.getParameter("proteins"));
            float carbohydrates = Float.valueOf(request.getParameter("carbohydrates"));

            Float calories = (proteins*4) + (carbohydrates*4) + (fats * 9);
            editProduct.setCalories(calories);
            editProduct.setFats(fats);
            editProduct.setCarbohydrates(carbohydrates);
            editProduct.setProteins(proteins);
            productService.update(editProduct);

        return "/menu/product";
    }
}
