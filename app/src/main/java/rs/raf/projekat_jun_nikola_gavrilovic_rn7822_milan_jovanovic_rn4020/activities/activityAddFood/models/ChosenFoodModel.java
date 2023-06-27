package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activityAddFood.models;

import java.util.Calendar;

public class ChosenFoodModel {
    private String foodName;
    private String category;
    private float calories;
    private Calendar date;

    public ChosenFoodModel(String foodName, String category, float calories, Calendar date) {
        this.foodName = foodName;
        this.category = category;
        this.calories = calories;
        this.date = date;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
