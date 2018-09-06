package controller.command.home;

import controller.command.CommandServlet;
import controller.command.CommandUtility;
import model.entity.Statistics;
import model.entity.User;
import model.services.portion.PortionService;
import model.services.product.ProductService;
import model.services.statistics.StatisticsService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import static view.TextConstant.*;

public class StatisticsInfo implements CommandServlet {
    private static final Logger logger = Logger.getLogger(StatisticsInfo.class);

    @Override
    public String execute(HttpServletRequest request) {
        float sumCalories = ZERO;
        int normCalories;
        String error;
        User user = CommandUtility.getLoginedUser(request.getSession());
        Calendar currentTime = Calendar.getInstance();
        Date date = new Date((currentTime.getTime()).getTime());
        String dateString = request.getParameter(DATE);
        StatisticsService statisticsService = new StatisticsService();

        int currentPage;
        int recordsPerPage;

        if(request.getParameter(DATE) == null) {
            currentPage = INIT_CURRENT_PAGE;
            recordsPerPage = INIT_RECORDS_PAGE;
        }else {
            currentPage = Integer.parseInt(request.getParameter(CURRENT_PAGE));
            recordsPerPage = Integer.parseInt(request.getParameter(RECORDS_PAGE));
            DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            try {
                java.util.Date sqlStartDate = dateFormat.parse(dateString);
                date = new java.sql.Date(sqlStartDate.getTime());

            } catch (ParseException e) {
                logger.error(e.getMessage());
            }
        }
        statisticsService.deleteAfterInsert(Date.valueOf(LocalDate.now()));

        normCalories = (int) user.getNormCalories();

        List<Statistics> statistics = statisticsService.findAll(currentPage,
                recordsPerPage, user.getId(), date);

        for (Statistics statistics1 : statistics) {
            sumCalories += (statistics1.getCalories() * statistics1.getAmount());
        }

        if(sumCalories > normCalories) {
            error = NORM + (int)(sumCalories-normCalories) +  CALORIES;
            request.setAttribute(ERROR, error);
        }

        request.setAttribute(STATISTICS, statistics);
        int rows = statisticsService.getNumberOfRows(user.getId(), date);
        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > ZERO) {
            nOfPages++;
        }

        request.setAttribute(DATE, date);
        request.setAttribute(NO_OF_PAGES, nOfPages);
        request.setAttribute(CURRENT_PAGE, currentPage);
        request.setAttribute(RECORDS_PAGE, recordsPerPage);
        return FORWARD_STATISTICS;
    }
}