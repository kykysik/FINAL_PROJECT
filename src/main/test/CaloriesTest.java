
import model.entity.User;
import model.services.user.CaloriesNorm;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

public class CaloriesTest  {
    private static User user;
    private static final String M = "M";
    private static final String F = "F";

    @BeforeClass
    public static void init() {
        user = new User();
        user.setLifeActivity(1.55f);
        user.setBirthDate(LocalDate.parse("1997-11-24"));
        user.setHeight(171);
        user.setWeight(72);
    }

    @Test
    public void maleUserCalories() {
        user.setGender(M);
        User resultUser = new CaloriesNorm().findNormCalories(user);
        Assert.assertTrue(2727 == (int)resultUser.getNormCalories());
        Assert.assertFalse(2000 == (int)resultUser.getNormCalories());
    }

    @Test
    public void FemaleUserCalories() {
        user.setGender(F);
        User resultUser = new CaloriesNorm().findNormCalories(user);
        Assert.assertTrue(2408 == (int)resultUser.getNormCalories());
        Assert.assertFalse(2000 == (int)resultUser.getNormCalories());
    }
}
