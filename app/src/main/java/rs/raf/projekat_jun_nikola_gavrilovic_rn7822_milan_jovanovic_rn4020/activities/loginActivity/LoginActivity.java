package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.loginActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.AppState;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.HomeActivity;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.application.MainApp;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.database.entities.UserEntity;

public class LoginActivity extends AppCompatActivity {

    private Button loginBtn;
    private EditText usernameTxt;
    private EditText passwordTxt;
    private TextView usernameLbl;
    private TextView passwordLbl;
    private ToggleButton toggleButtonShowPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initListeners();
    }

    private void initView() {

        loginBtn = findViewById(R.id.buttonLogin);
        toggleButtonShowPassword = findViewById(R.id.toggleButtonShowPassword);

        usernameTxt = findViewById(R.id.editTextUsername);
        passwordTxt = findViewById(R.id.editTextPassword);

        usernameLbl = findViewById(R.id.username_warning_text);
        passwordLbl = findViewById(R.id.password_warning_text);

    }

    public boolean checkUser(String name, String password) {
        /*boolean isValid = false;
        try {

            File fileR = new File(MainApp.getContext().getFilesDir(), "users.txt");
            BufferedReader bf = new BufferedReader(new FileReader(fileR));

            String str;
            String userName = "";
            String userPassword = "";
            while ((str = bf.readLine()) != null) {
                String[] userData = str.split(",");
                userName = userData[0];
                userPassword = userData[1];

                if (userName.equals(name) && userPassword.equals(password)) {
                    isValid = true;
                    break;
                }
            }
            System.out.println("Username: " + userName);
            System.out.println("Password: " + userPassword);
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isValid;*/
        UserEntity user = AppState.getInstance().getDb().userDao().findByUsername(name);
        //TODO: HASHOVATI PASSWORD
        return user != null && user.getPassword().equals(password);
    }

    private boolean checkPassword(String password) {
//      Password mora imati minimum 4 karaktera, barem jedno veliko slovo, jednu cifru, zabraniti specijalne karaktere (~#^|$%&*!)
        boolean ok = true;

        String specialRegex = "[~#^|$%&*!]";
        Pattern specialPattern = Pattern.compile(specialRegex);
        Matcher specialMatcher = specialPattern.matcher(password);
        if (specialMatcher.matches()) {
            System.out.println("Usao karakteri");
            ok = false;
        }

        String regex = "^(?=.*[A-Z])(?=.*[0-9]).+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            System.out.println("Usao broj i veliko slovo");
            ok = false;
        }

        if (password.length() < 4) {
            ok = false;
        }

        return ok;
    }

    private void initListeners() {
        toggleButtonShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButtonShowPassword.isChecked()) {
                    passwordTxt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    passwordTxt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        loginBtn.setOnClickListener(v -> {

            String username = "";
            String password = "";

            if (!usernameTxt.getText().toString().equals("")) {
                username = usernameTxt.getText().toString();
                usernameLbl.setVisibility(View.INVISIBLE);
            } else {
                usernameLbl.setVisibility(View.VISIBLE);
            }
            if (!passwordTxt.getText().toString().equals("")) {
                password = passwordTxt.getText().toString();
                passwordLbl.setVisibility(View.INVISIBLE);
            } else {
                passwordLbl.setVisibility(View.VISIBLE);
            }

            System.out.println("CheckUser " + checkUser(username, password));

            boolean validInput = true;

            if (!checkPassword(password)) {
                System.out.println("Usao password");
                validInput = false;
                Toast.makeText(this, "Pogresan Password", Toast.LENGTH_LONG).show();
            }

            if (validInput) {
                if (checkUser(username, password)) {
                    SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.apply();
                    Intent intent = new Intent(this, HomeActivity.class);
                    intent.putExtra("User", username);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Invalid user", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }


}
