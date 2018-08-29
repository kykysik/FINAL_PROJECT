package controller.command;

import model.entity.Product;
import model.services.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ProductInfo implements Command {
    @Override
    public String execute(HttpServletRequest request) {
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

      /*  if(request.getParameter(""))
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {

            if (!entry.getValue()[0].isEmpty()) {
                System.out.println(Arrays.toString(entry.getValue()));
                System.out.println(entry.getKey());
            }
        }
*/
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

        return "/WEB-INF/productList.jsp";
    }
}
