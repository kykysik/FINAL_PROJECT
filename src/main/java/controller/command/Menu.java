package controller.command;

import model.entity.Product;
import model.services.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class Menu implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/menu.jsp";


        /*
        ProductService productService = new ProductService();

        int currentPage;
        int recordsPerPage;
        if(request.getParameter("currentPage") == null) {
            currentPage = 1;
            recordsPerPage = 10;
        }else {
            currentPage = Integer.valueOf(request.getParameter("currentPage"));
            recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
        }

        List<Product> product = productService.findAllPortions(currentPage,
                recordsPerPage);

        request.setAttribute("product", product);

        int rows = productService.getNumberOfRowsPortions();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        return "/WEB-INF/menu.jsp";*/
    }
}