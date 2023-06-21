package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.enteties;

import android.graphics.Bitmap;

import java.util.List;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments.models.Category;

public class Food {

    private String ime;
    private String opis;
    private Category category;
    private String oblast;
    private String instrukcije;
    private Bitmap image;
    private List<String> tagovi;
    private String linkSnimku;
    private List<String> sastojci;


    public Food() {
    }
    /** Konstruktor za jela po kategorijama (Pocetna Strana) */
    public Food(String ime, String opis) {
        this.ime = ime;
        this.opis = opis;
    }

    /** Konstruktor za Lista Jela */
    public Food(String ime, Bitmap image) {
        this.ime = ime;
        this.image = image;
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
