package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainApp extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        MainApp.context = getApplicationContext();

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        if (isFirstRun) {

            try {
                File file = new File(context.getFilesDir(),"users.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write("Mobilne,Mobilne1\n");

                writer.close();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirstRun", false);
            editor.apply();
        }

    }

    public static Context getContext() {
        return context;
    }
}
