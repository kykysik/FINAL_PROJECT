package model.entity;

import java.sql.Date;
import java.time.LocalDate;

public class User {
    private int id;
    private String secondName;
    private String firstName;
    private String middleName;
    private String login;
    private String password;
    private String gender;
    private LocalDate birthDate; // глянуть
    private Float lifeActivity;
    private Float height;
    private Float weight;
    private Float normCalories;
    private String mail;
    private ROLE role = ROLE.UNKNOWN;

    public User() {

    }

    public User(String secondName, String firstName, String  middleName, String login, String password,
                String gender, LocalDate birthDate, Float lifeActivity, Float height, Float weight) {

        this.middleName = middleName;
        this.secondName = secondName;
        this.firstName = firstName;
        this.password = password;
        this.login = login;
        this.gender = gender;
        this.birthDate = birthDate;
        this.lifeActivity = lifeActivity;
        this.height = height;
        this.weight = weight;
    }

    public enum ROLE {
        USER, UNKNOWN, ADMIN
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getNormCalories() {
        return normCalories;
    }

    public void setNormCalories(float normCalories) {
        this.normCalories = normCalories;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public float getLifeActivity() {
        return lifeActivity;
    }

    public void setLifeActivity(float lifeActivity) {
        this.lifeActivity = lifeActivity;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
