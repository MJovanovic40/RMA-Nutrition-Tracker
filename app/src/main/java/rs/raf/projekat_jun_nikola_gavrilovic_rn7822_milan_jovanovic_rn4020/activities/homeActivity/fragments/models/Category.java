package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments.models;

import java.util.List;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood.models.Food;

public class Category {

    private String image;
    private String nazivKategorije;
    private String opisKategorije;
    private List<Food> listaJela;

    public Category() {
    }

    public Category(String image, String nazivKategorije, String opisKategorije) {
        this.image = image;
        this.nazivKategorije = nazivKategorije;
        this.opisKategorije = opisKategorije;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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

    public List<Food> getListaJela() {
        return listaJela;
    }

    public void setListaJela(List<Food> listaJela) {
        this.listaJela = listaJela;
    }
}
