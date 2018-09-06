package controller.command.home.menu.products;

import controller.command.CommandServlet;
import controller.command.CommandUtility;
import model.entity.Product;
import model.entity.User;
import model.services.portion.PortionService;
import model.services.product.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.*;

import static view.TextConstant.*;

public class ProductInfo implements CommandServlet {

    @Override
    public String execute(HttpServletRequest request) {
        ProductService productService = new ProductService();
        PortionService portionService = new PortionService();
        List<Integer> productId = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        String error = null;
        String id = request.getParameter(ID);
        String action = request.getParameter(ACTION);
        String temp = request.getParameter(MAP_ID);


        if(temp!= null) {
            request.getSession().setAttribute(MAP_ID, temp);
        }

        if(id != null) {
            productService.delete(Integer.parseInt(id));
        }

        int currentPage;
        int recordsPerPage;
        if(request.getParameter(CURRENT_PAGE) == null) {
            currentPage = INIT_CURRENT_PAGE;
            recordsPerPage = INIT_RECORDS_PAGE;
        }else {
             currentPage = Integer.parseInt(request.getParameter(CURRENT_PAGE));
             recordsPerPage = Integer.parseInt(request.getParameter(RECORDS_PAGE));
        }

        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {

            if (entry.getValue()[0].matches(REG_NUMBERS) && entry.getKey().contains(KEY_CONTAINS)) {
                count.add(Integer.parseInt(Arrays.toString(entry.getValue()).replaceAll(REG_BRACKETS, EMPTY)));
                productId.add(Integer.parseInt(entry.getKey().replaceAll(REG_ALL , EMPTY)));
            }
        }



        List<Product> product = productService.findAll(currentPage,
                recordsPerPage);

        request.setAttribute(PRODUCT, product);

        int rows = productService.getNumberOfRows();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > ZERO) {
            nOfPages++;
        }
        request.setAttribute(NO_OF_PAGES, nOfPages);
        request.setAttribute(CURRENT_PAGE, currentPage);
        request.setAttribute(RECORDS_PAGE, recordsPerPage);

        if(action != null) {
            switch (action) {
                case ADD_PRODUCTS:
                    if(request.getSession().getAttribute(MAP_ID) == null) {
                        return REDIRECT_PORTION;
                    }
                    temp = String.valueOf(request.getSession().getAttribute(MAP_ID));
                    temp = temp.replaceAll(REG_BRACKETS, EMPTY);
                    portionService.editPortion(count, productId, Integer.parseInt(temp));
                    request.getSession().setAttribute(MAP_ID, null);
                    return REDIRECT_PORTION;
                case MAKE_PORTION:
                    if(count.isEmpty()) {
                        error = SELECT_PRODUCT;
                        request.setAttribute(ERROR_PRODUCT, error);
                        break;
                    }
                    request.getSession().setAttribute(COUNT, count);
                    List listProduct =  productService.findAllById(productId);
                    request.getSession().setAttribute(LIST_PRODUCT, listProduct);
                    return FORWARD_NEW_PORTION;
                default:
                    if(count.isEmpty()) {
                        error = SELECT_PRODUCT;
                        request.setAttribute(ERROR_PRODUCT, error);
                        break;
                    }
                    User user = CommandUtility.getLoginedUser(request.getSession());
                    Calendar currentTime = Calendar.getInstance();
                    Date sqlDate = new Date((currentTime.getTime()).getTime());
                    productService.eatProduct(productId, count, user.getId(), sqlDate);
                    return REDIRECT_STATISTICS;
            }
        }

        return FORWARD_PRODUCT_LIST;
    }
}
