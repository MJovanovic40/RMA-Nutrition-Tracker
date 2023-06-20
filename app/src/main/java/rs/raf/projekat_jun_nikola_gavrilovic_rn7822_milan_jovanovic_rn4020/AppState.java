package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020;

public class AppState {
    private static AppState instance = null;

    public static AppState getInstance() {
        if(instance == null){
            instance = new AppState();
        }
        return instance;
    }


    private AppState(){}
}
