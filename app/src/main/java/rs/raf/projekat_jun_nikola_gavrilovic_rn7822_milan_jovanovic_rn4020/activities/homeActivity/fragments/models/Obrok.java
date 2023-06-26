package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments.models;

public class Obrok {

    private String ime;
    private String kategorija;
    private String kalorije;

    public Obrok() {
    }

    public Obrok(String ime, String kategorija, String kalorije) {
        this.ime = ime;
        this.kategorija = kategorija;
        this.kalorije = kalorije;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public String getKalorije() {
        return kalorije;
    }

    public void setKalorije(String kalorije) {
        this.kalorije = kalorije;
    }
}
