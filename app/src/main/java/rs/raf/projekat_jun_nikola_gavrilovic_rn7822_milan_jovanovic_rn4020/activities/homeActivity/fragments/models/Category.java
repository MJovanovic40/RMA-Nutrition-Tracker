package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments.models;

import android.graphics.Bitmap;

import java.util.List;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.enteties.Meal;

public class Category {

    private Bitmap image;
    private String nazivKategorije;
    private String opisKategorije;
    private List<Meal> listaJela;

    public Category() {
    }

    public Category(Bitmap image, String nazivKategorije, String opisKategorije) {
        this.image = image;
        this.nazivKategorije = nazivKategorije;
        this.opisKategorije = opisKategorije;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getNazivKategorije() {
        return nazivKategorije;
    }

    public void setNazivKategorije(String nazivKategorije) {
        this.nazivKategorije = nazivKategorije;
    }

    public String getOpisKategorije() {
        return opisKategorije;
    }

    public void setOpisKategorije(String opisKategorije) {
        this.opisKategorije = opisKategorije;
    }

    public List<Meal> getListaJela() {
        return listaJela;
    }

    public void setListaJela(List<Meal> listaJela) {
        this.listaJela = listaJela;
    }
}
