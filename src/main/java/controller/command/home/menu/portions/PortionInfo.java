package controller.command.home.menu.portions;

import controller.command.CommandServlet;
import controller.command.CommandUtility;
import model.entity.Portions;
import model.entity.Product;
import model.entity.User;
import model.services.portion.PortionService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.*;

import static view.TextConstant.*;

public class PortionInfo implements CommandServlet {
    @Override
    public String execute(HttpServletRequest request) {
        String error = null;
        List<Integer> portionsId = new ArrayList<>();
        List<Integer> amount = new ArrayList<>();
        PortionService portionService = new PortionService();
        String id = request.getParameter(P_ID);
        String namePortion = request.getParameter(NAME_PORTION);
        if(id != null) {
            portionService.delete(Integer.parseInt(id));
        }
        int currentPage;
        int recordsPerPage;
        if(request.getParameter(CURRENT_PAGE) == null) {
            currentPage = INIT_CURRENT_PAGE;
            recordsPerPage = INIT_RECORDS_PAGE;

        }else {
            currentPage = Integer.valueOf(request.getParameter(CURRENT_PAGE));
            recordsPerPage = Integer.valueOf(request.getParameter(RECORDS_PAGE));
        }

        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {

            if (entry.getValue()[0].matches(REG_NUMBERS) && entry.getKey().contains(KEY_CONTAINS)) {
                int countTemp = Integer.parseInt(Arrays.toString(entry.getValue()).replaceAll(REG_BRACKETS, EMPTY));
                int idTemp = Integer.parseInt(entry.getKey().replaceAll(REG_ALL , EMPTY));
                amount.add(countTemp);
                portionsId.add(idTemp);

            }
        }

        if(!amount.isEmpty()) {
            User user = CommandUtility.getLoginedUser(request.getSession());
            Calendar currentTime = Calendar.getInstance();
            java.sql.Date sqlDate = new Date((currentTime.getTime()).getTime());
            portionService.eatPortion(portionsId,amount, user.getId(), sqlDate);
            return REDIRECT_STATISTICS;
        }

        if(namePortion != null) {
            Float portionCalories = ZERO;
            List count = (List) request.getSession().getAttribute(COUNT);
            List listProduct = (List) request.getSession().getAttribute(LIST_PRODUCT);
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
                error = PORTION_EXIST;
                request.setAttribute(ERROR, error);
                return FORWARD_NEW_PORTION;
            }
            request.setAttribute(ERROR, null);

        }
        List<Portions> portion = portionService.findAllPortions(currentPage,
                recordsPerPage);

        request.setAttribute(PORTION, portion);

        int rows = portionService.getNumberOfRowsPortions();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > ZERO) {
            nOfPages++;
        }

        request.setAttribute(NO_OF_PAGES, nOfPages);
        request.setAttribute(CURRENT_PAGE, currentPage);
        request.setAttribute(RECORDS_PAGE, recordsPerPage);

        return FORWARD_PORTION_LIST;
    }
}
