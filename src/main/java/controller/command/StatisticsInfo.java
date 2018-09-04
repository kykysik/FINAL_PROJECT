package controller.command;

import model.entity.Portions;
import model.entity.Product;
import model.entity.Statistics;
import model.entity.User;
import model.services.CaloriesNorm;
import model.services.PortionService;
import model.services.ProductService;
import model.services.StatisticsService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

public class StatisticsInfo implements CommandServlet {
    @Override
    public String execute(HttpServletRequest request) {
        float sumCalories = 0f;
        int normCalories;
        String error;
        CaloriesNorm caloriesNorm = new CaloriesNorm();
        User user = CommandUtility.getLoginedUser(request.getSession());
        Calendar currentTime = Calendar.getInstance();
        Date date = new Date((currentTime.getTime()).getTime());
        String dateString = request.getParameter("date");
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        StatisticsService statisticsService = new StatisticsService();
        PortionService portionService = new PortionService();
        ProductService productService = new ProductService();
        int currentPage;
        int recordsPerPage;

        if(request.getParameter("date") == null) {
            currentPage = 1;
            recordsPerPage = 5;
        }else {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                java.util.Date sqlStartDate = dateFormat.parse(dateString);
                date = new java.sql.Date(sqlStartDate.getTime());

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        statisticsService.deleteAfterInsert(Date.valueOf(LocalDate.now()));


        if(type != null && type.equals("portion")) {
            System.out.println("LEL");
            System.out.println(id);
            portionService.deleteUserPortion(Integer.parseInt(id));
            return "/statistics";
        }else if(type != null && type.equals("product")) {
            System.out.println("FD");
            System.out.println(id);
            productService.deleteUserProduct(Integer.parseInt(id));
            return "/statistics";
        }

        normCalories = (int) user.getNormCalories();

        List<Statistics> statistics = statisticsService.findAll(currentPage,
                recordsPerPage, user.getId(), date);

        for (Statistics statistics1 : statistics) {
            sumCalories += (statistics1.getCalories() * statistics1.getAmount());
        }

        if(sumCalories > normCalories) {
            error = "Your norm is exceeded on " + (sumCalories-normCalories) +  " calories";
            request.setAttribute("error", error);
        }

        request.setAttribute("statistics", statistics);
        int rows = statisticsService.getNumberOfRows(user.getId(), date);
        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("date", date);
        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        return "/WEB-INF/statistics.jsp";
    }
}