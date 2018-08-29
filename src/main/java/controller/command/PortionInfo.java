package controller.command;

import model.entity.Portions;
import model.services.PortionService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class PortionInfo implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        PortionService portionService = new PortionService();

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

            /* Сравнить count && select. Если тру - добавляем в список.
            * После этот список пихаем в таблицу*/
            if (!entry.getValue()[0].isEmpty()) {
                System.out.println(Arrays.toString(entry.getValue()));
                System.out.println(entry.getKey());
            }
        }



        System.out.println("------------------------------------");
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
