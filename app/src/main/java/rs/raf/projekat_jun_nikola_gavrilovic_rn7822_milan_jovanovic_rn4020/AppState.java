package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020;

import androidx.room.Room;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.database.AppDatabase;

public class AppState {
    private static AppState instance = null;

    private AppDatabase db;

    public static AppState getInstance() {
        if(instance == null){
            instance = new AppState();
        }
        return instance;
    }


    private AppState(){
    }

    public AppDatabase getDb() {
        return db;
    }

    public void setDb(AppDatabase db) {
        this.db = db;
    }
}
