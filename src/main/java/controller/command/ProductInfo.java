package controller.command;

import model.entity.Product;
import model.entity.User;
import model.services.PortionService;
import model.services.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.*;

public class ProductInfo implements CommandServlet {
    @Override
    public String execute(HttpServletRequest request) {
        ProductService productService = new ProductService();
        PortionService portionService = new PortionService();
        List<Integer> productId = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        String error = null;
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        String temp = request.getParameter("mapId");


        if(temp!= null) {
            request.getSession().setAttribute("mapId", temp);
        }

        if(id != null) {
            productService.delete(Integer.parseInt(id));
        }

        int currentPage;
        int recordsPerPage;
        if(request.getParameter("currentPage") == null) {
            currentPage = 1;
            recordsPerPage = 10;
        }else {
             currentPage = Integer.parseInt(request.getParameter("currentPage"));
             recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        }

        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {

            if (entry.getValue()[0].matches("^[0-9]+$") && entry.getKey().contains("_")) {
                count.add(Integer.parseInt(Arrays.toString(entry.getValue()).replaceAll("\\p{P}", "")));
                productId.add(Integer.parseInt(entry.getKey().replaceAll(".*_" , "")));
            }
        }



        List<Product> product = productService.findAll(currentPage,
                recordsPerPage);

        request.setAttribute("product", product);

        int rows = productService.getNumberOfRows();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }
        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        if(action != null) {
            switch (action) {
                case "addProducts":
                    if(request.getSession().getAttribute("mapId") == null) {
                        return "/menu/portion";
                    }
                    temp = String.valueOf(request.getSession().getAttribute("mapId"));
                    temp = temp.replaceAll("\\p{P}", "");
                    portionService.editPortion(count, productId, Integer.parseInt(temp));
                    request.getSession().setAttribute("mapId", null); // Посмотреть тут.
                    return "/menu/portion";
                case "makePortion":
                    if(count.isEmpty()) {
                        error = "Please, select any product";
                        request.setAttribute("errorProduct", error);
                        break;
                    }
                    request.getSession().setAttribute("count", count);
                    List listProduct =  productService.findAllById(productId);
                    request.getSession().setAttribute("listProduct", listProduct);
                    return "/WEB-INF/newPortion.jsp";
                default:
                    if(count.isEmpty()) {
                        error = "Please, select any product";
                        request.setAttribute("errorProduct", error);
                        break;
                    }
                    User user = CommandUtility.getLoginedUser(request.getSession());
                    Calendar currenttime = Calendar.getInstance();
                    Date sqldate = new Date((currenttime.getTime()).getTime());
                    productService.eatProduct(productId, count, user.getId(), sqldate);
                    /*После добавления - кидает на статистику,
                    * где мы уже будет доставать все.
                    * Сделать выборку по дню календаря.*/
                    return "/statistics";
            }
        }

        return "/WEB-INF/productList.jsp";
    }
}
