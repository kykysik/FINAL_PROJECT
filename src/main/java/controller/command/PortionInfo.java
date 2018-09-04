package controller.command;

import model.entity.Portions;
import model.entity.Product;
import model.entity.User;
import model.services.PortionService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.*;

public class PortionInfo implements CommandServlet {
    @Override
    public String execute(HttpServletRequest request) {
        String error = null;
        List<Integer> portionsId = new ArrayList<>();
        List<Integer> amount = new ArrayList<>();
        PortionService portionService = new PortionService();
        String id = request.getParameter("pId");
        String namePortion = request.getParameter("namePortion");
        if(id != null) {
            portionService.delete(Integer.parseInt(id));
        }
        int currentPage;
        int recordsPerPage;
        if(request.getParameter("currentPage") == null) {
            currentPage = 1;
            recordsPerPage = 5;

        }else {
            currentPage = Integer.valueOf(request.getParameter("currentPage"));
            recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
        }

        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {

            if (entry.getValue()[0].matches("^[0-9]+$") && entry.getKey().contains("_")) {
                int countTemp = Integer.parseInt(Arrays.toString(entry.getValue()).replaceAll("\\p{P}", ""));
                int idTemp = Integer.parseInt(entry.getKey().replaceAll(".*_" , ""));
                amount.add(countTemp);
                portionsId.add(idTemp);

            }
        }

        if(!amount.isEmpty()) {
            User user = CommandUtility.getLoginedUser(request.getSession());
            Calendar currentTime = Calendar.getInstance();
            java.sql.Date sqlDate = new Date((currentTime.getTime()).getTime());
            portionService.eatPortion(portionsId,amount, user.getId(), sqlDate);
            return "/statistics";
        }

        if(namePortion != null) {
            Float portionCalories = 0f;
            List count = (List) request.getSession().getAttribute("count");
            List listProduct = (List) request.getSession().getAttribute("listProduct");
            for(int i = 0; i < listProduct.size(); i++){
               Product product = (Product) listProduct.get(i);
               int temp = (int) count.get(i);

               portionCalories += (product.getCalories() * temp);
            }

            Portions newPortion = new Portions();
            newPortion.setName(namePortion);
            newPortion.setCalories(portionCalories);
            boolean state =  portionService.create(newPortion);
            if(state) {
                Portions portionId = portionService.findPortion(newPortion);
                portionService.createPortionProduct(listProduct, count, portionId.getId());
            }else {
                error = "Portion with this name is exist";
                request.setAttribute("error", error);
                return "/WEB-INF/newPortion.jsp";
            }
            request.setAttribute("error", null);

        }
        List<Portions> portion = portionService.findAllPortions(currentPage,
                recordsPerPage);

        request.setAttribute("portion", portion);

        int rows = portionService.getNumberOfRowsPortions();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        return "/WEB-INF/portionList.jsp";
    }
}
