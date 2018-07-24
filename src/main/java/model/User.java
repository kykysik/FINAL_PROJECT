package model;


public class User {

    private String secondName;
    private String firstName;
    private String middleName;
    private String login;
    private String password;

    public User() {

    }

    public User(String secondName, String firstName, String  middleName, String login, String password) {

        this.middleName = middleName;
        this.secondName = secondName;
        this.firstName = firstName;
        this.password = password;
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return secondName.
     */
    public String getLogin() {
        return login;
    }

    /**
     *This method set string value in secondName
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
