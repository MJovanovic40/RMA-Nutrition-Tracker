package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.lang.reflect.Field;
import java.util.Date;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.meal.DetailedMealResponse;

@Entity
public class MealEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "str_meal")
    private String strMeal;

    @ColumnInfo(name = "str_drink_alternate")
    private String strDrinkAlternate;

    @ColumnInfo(name = "str_category")
    private String strCategory;

    @ColumnInfo(name = "str_area")
    private String strArea;

    @ColumnInfo(name = "str_instructions")
    private String strInstructions;

    @ColumnInfo(name = "str_meal_thumb")
    private String strMealThumb;

    @ColumnInfo(name = "str_tags")
    private String strTags;

    @ColumnInfo(name = "str_youtube")
    private String strYoutube;

    @ColumnInfo(name = "str_ingredient1")
    private String strIngredient1;

    @ColumnInfo(name = "str_ingredient2")
    private String strIngredient2;

    @ColumnInfo(name = "str_ingredient3")
    private String strIngredient3;

    @ColumnInfo(name = "str_ingredient4")
    private String strIngredient4;

    @ColumnInfo(name = "str_ingredient5")
    private String strIngredient5;

    @ColumnInfo(name = "str_ingredient6")
    private String strIngredient6;

    @ColumnInfo(name = "str_ingredient7")
    private String strIngredient7;

    @ColumnInfo(name = "str_ingredient8")
    private String strIngredient8;

    @ColumnInfo(name = "str_ingredient9")
    private String strIngredient9;

    @ColumnInfo(name = "str_ingredient10")
    private String strIngredient10;

    @ColumnInfo(name = "str_ingredient11")
    private String strIngredient11;

    @ColumnInfo(name = "str_ingredient12")
    private String strIngredient12;

    @ColumnInfo(name = "str_ingredient13")
    private String strIngredient13;

    @ColumnInfo(name = "str_ingredient14")
    private String strIngredient14;

    @ColumnInfo(name = "str_ingredient15")
    private String strIngredient15;

    @ColumnInfo(name = "str_ingredient16")
    private String strIngredient16;

    @ColumnInfo(name = "str_ingredient17")
    private String strIngredient17;

    @ColumnInfo(name = "str_ingredient18")
    private String strIngredient18;

    @ColumnInfo(name = "str_ingredient19")
    private String strIngredient19;

    @ColumnInfo(name = "str_ingredient20")
    private String strIngredient20;

    @ColumnInfo(name = "str_measure1")
    private String strMeasure1;

    @ColumnInfo(name = "str_measure2")
    private String strMeasure2;

    @ColumnInfo(name = "str_measure3")
    private String strMeasure3;

    @ColumnInfo(name = "str_measure4")
    private String strMeasure4;

    @ColumnInfo(name = "str_measure5")
    private String strMeasure5;

    @ColumnInfo(name = "str_measure6")
    private String strMeasure6;

    @ColumnInfo(name = "str_measure7")
    private String strMeasure7;

    @ColumnInfo(name = "str_measure8")
    private String strMeasure8;

    @ColumnInfo(name = "str_measure9")
    private String strMeasure9;

    @ColumnInfo(name = "str_measure10")
    private String strMeasure10;

    @ColumnInfo(name = "str_measure11")
    private String strMeasure11;

    @ColumnInfo(name = "str_measure12")
    private String strMeasure12;

    @ColumnInfo(name = "str_measure13")
    private String strMeasure13;

    @ColumnInfo(name = "str_measure14")
    private String strMeasure14;

    @ColumnInfo(name = "str_measure15")
    private String strMeasure15;

    @ColumnInfo(name = "str_measure16")
    private String strMeasure16;

    @ColumnInfo(name = "str_measure17")
    private String strMeasure17;

    @ColumnInfo(name = "str_measure18")
    private String strMeasure18;

    @ColumnInfo(name = "str_measure19")
    private String strMeasure19;

    @ColumnInfo(name = "str_measure20")
    private String strMeasure20;

    @ColumnInfo(name = "str_source")
    private String strSource;

    @ColumnInfo(name = "str_image_source")
    private String strImageSource;

    @ColumnInfo(name = "str_creative_commons_confirmed")
    private String strCreativeCommonsConfirmed;

    @ColumnInfo(name = "date_saved")
    private Date dateSaved;

    @ColumnInfo(name = "preparation_date")
    private Date preparationDate;

    @ColumnInfo(name = "meal_category")
    private String mealCategory;


    @ColumnInfo(name = "calories")
    private float calories;

    public MealEntity(){}
    public MealEntity(DetailedMealResponse dmr, String image, Date preparationDate, String mealCategory, float calories) {
        //this.id = Integer.parseInt(dmr.getIdMeal());
        this.strMeal = dmr.getStrMeal();
        this.strDrinkAlternate = dmr.getStrDrinkAlternate();
        this.strCategory = dmr.getStrCategory();
        this.strArea = dmr.getStrArea();
        this.strInstructions = dmr.getStrInstructions();
        this.strMealThumb = image;
        this.strTags = dmr.getStrTags();
        this.strYoutube = dmr.getStrYoutube();

        this.strIngredient1 = dmr.getStrIngredient1();
        this.strIngredient2 = dmr.getStrIngredient2();
        this.strIngredient3 = dmr.getStrIngredient3();
        this.strIngredient4 = dmr.getStrIngredient4();
        this.strIngredient5 = dmr.getStrIngredient5();
        this.strIngredient6 = dmr.getStrIngredient6();
        this.strIngredient7 = dmr.getStrIngredient7();
        this.strIngredient8 = dmr.getStrIngredient8();
        this.strIngredient9 = dmr.getStrIngredient9();
        this.strIngredient10 = dmr.getStrIngredient10();
        this.strIngredient11 = dmr.getStrIngredient11();
        this.strIngredient12 = dmr.getStrIngredient12();
        this.strIngredient13 = dmr.getStrIngredient13();
        this.strIngredient14 = dmr.getStrIngredient14();
        this.strIngredient15 = dmr.getStrIngredient15();
        this.strIngredient16 = dmr.getStrIngredient16();
        this.strIngredient17 = dmr.getStrIngredient17();
        this.strIngredient18 = dmr.getStrIngredient18();
        this.strIngredient19 = dmr.getStrIngredient19();
        this.strIngredient20 = dmr.getStrIngredient20();

        this.strMeasure1 = dmr.getStrMeasure1();
        this.strMeasure2 = dmr.getStrMeasure2();
        this.strMeasure3 = dmr.getStrMeasure3();
        this.strMeasure4 = dmr.getStrMeasure4();
        this.strMeasure5 = dmr.getStrMeasure5();
        this.strMeasure6 = dmr.getStrMeasure6();
        this.strMeasure7 = dmr.getStrMeasure7();
        this.strMeasure8 = dmr.getStrMeasure8();
        this.strMeasure9 = dmr.getStrMeasure9();
        this.strMeasure10 = dmr.getStrMeasure10();
        this.strMeasure11 = dmr.getStrMeasure11();
        this.strMeasure12 = dmr.getStrMeasure12();
        this.strMeasure13 = dmr.getStrMeasure13();
        this.strMeasure14 = dmr.getStrMeasure14();
        this.strMeasure15 = dmr.getStrMeasure15();
        this.strMeasure16 = dmr.getStrMeasure16();
        this.strMeasure17 = dmr.getStrMeasure17();
        this.strMeasure18 = dmr.getStrMeasure18();
        this.strMeasure19 = dmr.getStrMeasure19();
        this.strMeasure20 = dmr.getStrMeasure20();

        this.strSource = dmr.getStrSource();
        this.strImageSource = dmr.getStrImageSource();
        this.strCreativeCommonsConfirmed = dmr.getStrCreativeCommonsConfirmed();
        this.dateSaved = new Date();
        this.preparationDate = preparationDate;
        this.mealCategory = mealCategory;

        this.calories = calories;

    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public String getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory = mealCategory;
    }

    public Date getDateSaved() {
        return dateSaved;
    }

    public void setDateSaved(Date dateSaved) {
        this.dateSaved = dateSaved;
    }

    public Date getPreparationDate() {
        return preparationDate;
    }

    public void setPreparationDate(Date preparationDate) {
        this.preparationDate = preparationDate;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrDrinkAlternate() {
        return strDrinkAlternate;
    }

    public void setStrDrinkAlternate(String strDrinkAlternate) {
        this.strDrinkAlternate = strDrinkAlternate;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getStrTags() {
        return strTags;
    }

    public void setStrTags(String strTags) {
        this.strTags = strTags;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    public String getStrIngredient1() {
        return strIngredient1;
    }

    public void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    public String getStrIngredient2() {
        return strIngredient2;
    }

    public void setStrIngredient2(String strIngredient2) {
        this.strIngredient2 = strIngredient2;
    }

    public String getStrIngredient3() {
        return strIngredient3;
    }

    public void setStrIngredient3(String strIngredient3) {
        this.strIngredient3 = strIngredient3;
    }

    public String getStrIngredient4() {
        return strIngredient4;
    }

    public void setStrIngredient4(String strIngredient4) {
        this.strIngredient4 = strIngredient4;
    }

    public String getStrIngredient5() {
        return strIngredient5;
    }

    public void setStrIngredient5(String strIngredient5) {
        this.strIngredient5 = strIngredient5;
    }

    public String getStrIngredient6() {
        return strIngredient6;
    }

    public void setStrIngredient6(String strIngredient6) {
        this.strIngredient6 = strIngredient6;
    }

    public String getStrIngredient7() {
        return strIngredient7;
    }

    public void setStrIngredient7(String strIngredient7) {
        this.strIngredient7 = strIngredient7;
    }

    public String getStrIngredient8() {
        return strIngredient8;
    }

    public void setStrIngredient8(String strIngredient8) {
        this.strIngredient8 = strIngredient8;
    }

    public String getStrIngredient9() {
        return strIngredient9;
    }

    public void setStrIngredient9(String strIngredient9) {
        this.strIngredient9 = strIngredient9;
    }

    public String getStrIngredient10() {
        return strIngredient10;
    }

    public void setStrIngredient10(String strIngredient10) {
        this.strIngredient10 = strIngredient10;
    }

    public String getStrIngredient11() {
        return strIngredient11;
    }

    public void setStrIngredient11(String strIngredient11) {
        this.strIngredient11 = strIngredient11;
    }

    public String getStrIngredient12() {
        return strIngredient12;
    }

    public void setStrIngredient12(String strIngredient12) {
        this.strIngredient12 = strIngredient12;
    }

    public String getStrIngredient13() {
        return strIngredient13;
    }

    public void setStrIngredient13(String strIngredient13) {
        this.strIngredient13 = strIngredient13;
    }

    public String getStrIngredient14() {
        return strIngredient14;
    }

    public void setStrIngredient14(String strIngredient14) {
        this.strIngredient14 = strIngredient14;
    }

    public String getStrIngredient15() {
        return strIngredient15;
    }

    public void setStrIngredient15(String strIngredient15) {
        this.strIngredient15 = strIngredient15;
    }

    public String getStrIngredient16() {
        return strIngredient16;
    }

    public void setStrIngredient16(String strIngredient16) {
        this.strIngredient16 = strIngredient16;
    }

    public String getStrIngredient17() {
        return strIngredient17;
    }

    public void setStrIngredient17(String strIngredient17) {
        this.strIngredient17 = strIngredient17;
    }

    public String getStrIngredient18() {
        return strIngredient18;
    }

    public void setStrIngredient18(String strIngredient18) {
        this.strIngredient18 = strIngredient18;
    }

    public String getStrIngredient19() {
        return strIngredient19;
    }

    public void setStrIngredient19(String strIngredient19) {
        this.strIngredient19 = strIngredient19;
    }

    public String getStrIngredient20() {
        return strIngredient20;
    }

    public void setStrIngredient20(String strIngredient20) {
        this.strIngredient20 = strIngredient20;
    }

    public String getStrMeasure1() {
        return strMeasure1;
    }

    public void setStrMeasure1(String strMeasure1) {
        this.strMeasure1 = strMeasure1;
    }

    public String getStrMeasure2() {
        return strMeasure2;
    }

    public void setStrMeasure2(String strMeasure2) {
        this.strMeasure2 = strMeasure2;
    }

    public String getStrMeasure3() {
        return strMeasure3;
    }

    public void setStrMeasure3(String strMeasure3) {
        this.strMeasure3 = strMeasure3;
    }

    public String getStrMeasure4() {
        return strMeasure4;
    }

    public void setStrMeasure4(String strMeasure4) {
        this.strMeasure4 = strMeasure4;
    }

    public String getStrMeasure5() {
        return strMeasure5;
    }

    public void setStrMeasure5(String strMeasure5) {
        this.strMeasure5 = strMeasure5;
    }

    public String getStrMeasure6() {
        return strMeasure6;
    }

    public void setStrMeasure6(String strMeasure6) {
        this.strMeasure6 = strMeasure6;
    }

    public String getStrMeasure7() {
        return strMeasure7;
    }

    public void setStrMeasure7(String strMeasure7) {
        this.strMeasure7 = strMeasure7;
    }

    public String getStrMeasure8() {
        return strMeasure8;
    }

    public void setStrMeasure8(String strMeasure8) {
        this.strMeasure8 = strMeasure8;
    }

    public String getStrMeasure9() {
        return strMeasure9;
    }

    public void setStrMeasure9(String strMeasure9) {
        this.strMeasure9 = strMeasure9;
    }

    public String getStrMeasure10() {
        return strMeasure10;
    }

    public void setStrMeasure10(String strMeasure10) {
        this.strMeasure10 = strMeasure10;
    }

    public String getStrMeasure11() {
        return strMeasure11;
    }

    public void setStrMeasure11(String strMeasure11) {
        this.strMeasure11 = strMeasure11;
    }

    public String getStrMeasure12() {
        return strMeasure12;
    }

    public void setStrMeasure12(String strMeasure12) {
        this.strMeasure12 = strMeasure12;
    }

    public String getStrMeasure13() {
        return strMeasure13;
    }

    public void setStrMeasure13(String strMeasure13) {
        this.strMeasure13 = strMeasure13;
    }

    public String getStrMeasure14() {
        return strMeasure14;
    }

    public void setStrMeasure14(String strMeasure14) {
        this.strMeasure14 = strMeasure14;
    }

    public String getStrMeasure15() {
        return strMeasure15;
    }

    public void setStrMeasure15(String strMeasure15) {
        this.strMeasure15 = strMeasure15;
    }

    public String getStrMeasure16() {
        return strMeasure16;
    }

    public void setStrMeasure16(String strMeasure16) {
        this.strMeasure16 = strMeasure16;
    }

    public String getStrMeasure17() {
        return strMeasure17;
    }

    public void setStrMeasure17(String strMeasure17) {
        this.strMeasure17 = strMeasure17;
    }

    public String getStrMeasure18() {
        return strMeasure18;
    }

    public void setStrMeasure18(String strMeasure18) {
        this.strMeasure18 = strMeasure18;
    }

    public String getStrMeasure19() {
        return strMeasure19;
    }

    public void setStrMeasure19(String strMeasure19) {
        this.strMeasure19 = strMeasure19;
    }

    public String getStrMeasure20() {
        return strMeasure20;
    }

    public void setStrMeasure20(String strMeasure20) {
        this.strMeasure20 = strMeasure20;
    }

    public String getStrSource() {
        return strSource;
    }

    public void setStrSource(String strSource) {
        this.strSource = strSource;
    }

    public String getStrImageSource() {
        return strImageSource;
    }

    public void setStrImageSource(String strImageSource) {
        this.strImageSource = strImageSource;
    }

    public String getStrCreativeCommonsConfirmed() {
        return strCreativeCommonsConfirmed;
    }

    public void setStrCreativeCommonsConfirmed(String strCreativeCommonsConfirmed) {
        this.strCreativeCommonsConfirmed = strCreativeCommonsConfirmed;
    }

    public Date getDateModified() {
        return dateSaved;
    }

    public void setDateModified(Date dateSaved) {
        this.dateSaved = dateSaved;
    }

    public void setIngredient(int index, String ingredient) {
        try {
            Field field = getClass().getDeclaredField("strIngredient" + index);
            field.setAccessible(true);
            field.set(this, ingredient);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public String getIngredient(int index) {
        try {
            Field field = getClass().getDeclaredField("strIngredient" + index);
            field.setAccessible(true);
            return (String) field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setMeasure(int index, String measure) {
        try {
            Field field = getClass().getDeclaredField("strMeasure" + index);
            field.setAccessible(true);
            field.set(this, measure);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public String getMeasure(int index) {
        try {
            Field field = getClass().getDeclaredField("strMeasure" + index);
            field.setAccessible(true);
            return (String) field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getSastojci(){
        StringBuilder builder = new StringBuilder();
        for(int i = 1; i <= 20; i++){
            String ingredient = getIngredient(i);
            if (ingredient == null || ingredient.isEmpty()) {
                continue;
            }
            builder.append(getMeasure(i)).append(" ");
            builder.append(getIngredient(i)).append(", ");
        }
        if(builder.length() > 2){
            builder.deleteCharAt(builder.length()-1); //Delete space
            builder.deleteCharAt(builder.length()-1); //Delete ','
        }

        return builder.toString();
    }
}
