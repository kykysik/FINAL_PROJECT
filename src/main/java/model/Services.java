package model;


public class Services {

    private User model;
    private String regName;
    private String regLogin;

    public Services(User model, String regName, String regLogin){
        this.model = model;
        this.regName = regName;
        this.regLogin = regLogin;
    }

    /**
     * verifies the SecondName for correctness
     * @param res
     * @return true, if regEx is correct
     */
    public boolean checkSecondName(String res) {

        if(res.matches(regName)) {
            model.setSecondName(res);
            return true;
        }
        return false;
    }

    /**
     * verifies the FirstName for correctness
     * @param res
     * @return true, if regEx is correct
     */
    public boolean checkFirstName(String res) {
        if(res.matches(regName)) {
            model.setFirstName(res);
            return true;
        }
        return false;
    }

    /**
     * verifies the MiddleName for correctness
     * @param res
     * @return true, if regEx is correct
     */
    public boolean checkMiddleName(String res) {

        if(res.matches(regName)) {
            model.setMiddleName(res);
            return true;
        }
        return false;
    }

    public boolean checkLogin(String res) {
        if(res.matches(regLogin)) {
            model.setLogin(res);
            return true;
        }
        return false;
    }
}
