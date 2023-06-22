package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.enteties;

import android.graphics.Bitmap;

import java.util.List;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments.models.Category;

public class Food {

    private String id;
    private String ime;
    private String opis;
    private Category category;
    private String oblast;
    private String instrukcije;
    private String image;
    private List<String> tagovi;
    private String linkSnimku;
    private List<String> sastojci;
    private float calories;


    public Food() {
    }
    /** Konstruktor za jela po kategorijama (Pocetna Strana) */
    public Food(String ime, String opis, String id) {
        this.ime = ime;
        this.opis = opis;
        this.id = id;
    }

    public Food(String ime, String opis, float calories) {
        this.ime = ime;
        this.opis = opis;
        this.calories = calories;
    }

    /** Konstruktor za Lista Jela */
    public Food(String ime, String image) {
        this.ime = ime;
        this.image = image;
    }

    public Food(String ime, float calories, String image) {
        this.ime = ime;
        this.image = image;
        this.calories = calories;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getOblast() {
        return oblast;
    }

    public void setOblast(String oblast) {
        this.oblast = oblast;
    }

    public String getInstrukcije() {
        return instrukcije;
    }

    public void setInstrukcije(String instrukcije) {
        this.instrukcije = instrukcije;
    }

    public List<String> getTagovi() {
        return tagovi;
    }

    public void setTagovi(List<String> tagovi) {
        this.tagovi = tagovi;
    }

    public String getLinkSnimku() {
        return linkSnimku;
    }

    public void setLinkSnimku(String linkSnimku) {
        this.linkSnimku = linkSnimku;
    }

    public List<String> getSastojci() {
        return sastojci;
    }

    public void setSastojci(List<String> sastojci) {
        this.sastojci = sastojci;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
