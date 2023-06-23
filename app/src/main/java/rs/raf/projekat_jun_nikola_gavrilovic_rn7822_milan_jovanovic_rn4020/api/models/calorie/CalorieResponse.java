package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.calorie;

public class CalorieResponse {
    private String name;
    private float calories;
    private float serving_size_g;
    private float fat_total_g;
    private float fat_saturated_g;
    private float protein_g;
    private float sodium_mg;
    private float potassium_mg;
    private float cholesterol_mg;
    private float carbohydrates_total_g;
    private float fiber_g;
    private float sugar_g;


    // Getter Methods

    public String getName() {
        return name;
    }

    public float getCalories() {
        return calories;
    }

    public float getServing_size_g() {
        return serving_size_g;
    }

    public float getFat_total_g() {
        return fat_total_g;
    }

    public float getFat_saturated_g() {
        return fat_saturated_g;
    }

    public float getProtein_g() {
        return protein_g;
    }

    public float getSodium_mg() {
        return sodium_mg;
    }

    public float getPotassium_mg() {
        return potassium_mg;
    }

    public float getCholesterol_mg() {
        return cholesterol_mg;
    }

    public float getCarbohydrates_total_g() {
        return carbohydrates_total_g;
    }

    public float getFiber_g() {
        return fiber_g;
    }

    public float getSugar_g() {
        return sugar_g;
    }

    // Setter Methods

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public void setServing_size_g(float serving_size_g) {
        this.serving_size_g = serving_size_g;
    }

    public void setFat_total_g(float fat_total_g) {
        this.fat_total_g = fat_total_g;
    }

    public void setFat_saturated_g(float fat_saturated_g) {
        this.fat_saturated_g = fat_saturated_g;
    }

    public void setProtein_g(float protein_g) {
        this.protein_g = protein_g;
    }

    public void setSodium_mg(float sodium_mg) {
        this.sodium_mg = sodium_mg;
    }

    public void setPotassium_mg(float potassium_mg) {
        this.potassium_mg = potassium_mg;
    }

    public void setCholesterol_mg(float cholesterol_mg) {
        this.cholesterol_mg = cholesterol_mg;
    }

    public void setCarbohydrates_total_g(float carbohydrates_total_g) {
        this.carbohydrates_total_g = carbohydrates_total_g;
    }

    public void setFiber_g(float fiber_g) {
        this.fiber_g = fiber_g;
    }

    public void setSugar_g(float sugar_g) {
        this.sugar_g = sugar_g;
    }
}
