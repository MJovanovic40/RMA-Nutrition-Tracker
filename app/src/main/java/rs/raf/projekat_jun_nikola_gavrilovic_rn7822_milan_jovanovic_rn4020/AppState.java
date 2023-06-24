package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020;

import androidx.room.Room;

import java.util.List;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments.models.Category;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.database.AppDatabase;

public class AppState {
    private static AppState instance = null;
    private AppDatabase db;
    private List<Category> categories;

    public static AppState getInstance() {
        if(instance == null){
            instance = new AppState();
        }
        return instance;
    }


    private AppState(){
    }

    public static void setInstance(AppState instance) {
        AppState.instance = instance;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public AppDatabase getDb() {
        return db;
    }

    public void setDb(AppDatabase db) {
        this.db = db;
    }
}
