package model.entity;


public class Portions {
    private int id;
    private String name;
    private float calories;
    private int amount;

     public Portions() {

     }

     public Portions(int id, String name, float calories, int amount) {
         this.id = id;
         this.name = name;
         this.calories = calories;
         this.amount = amount;
     }

    public Portions(int id, String name, float calories) {
        this.id = id;
        this.name = name;
        this.calories = calories;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }
}
