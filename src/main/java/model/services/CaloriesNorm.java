package model.services;

import model.entity.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CaloriesNorm {
    public User findNormCalories(User user) {
        double BMR;
        double norm;
        LocalDate date = user.getBirthDate();
        LocalDate now =  LocalDate.now();
        LocalDate start = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
        LocalDate end = LocalDate.of(now.getYear(), now.getMonth(), now.getDayOfMonth());
        long years = ChronoUnit.YEARS.between(start, end);
        if(user.getGender().equals("M")){
            BMR = (88.36 + (13.4 * user.getWeight()) + (4.8 * user.getHeight()) - (5.7 * years));
            norm = BMR * user.getLifeActivity();
        }else {
            BMR = (447.6 + (9.2 * user.getWeight()) + (3.1 * user.getHeight()) - (4.3 * years));
            norm = BMR * user.getLifeActivity();
        }
        user.setNormCalories((float) norm);
        return user;
    }

}
