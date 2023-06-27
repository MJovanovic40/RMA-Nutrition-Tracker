package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.AppState;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activityProfile.EditDataActivity;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activityProfile.StatisticsActivity;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.loginActivity.LoginActivity;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.database.entities.UserEntity;

public class ProfileFragment extends Fragment {

    private Button statisticsButton;
    private Button editDataButton;
    private Button logoutButton;

    private TextView usernameTextView;

    private TextView ageTextView;

    private TextView heightTextView;

    private TextView weightTextView;

    private TextView genderTextView;

    private TextView activityLevelTextView;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        statisticsButton = view.findViewById(R.id.statisticsButton);
        editDataButton = view.findViewById(R.id.editDataButton);
        logoutButton = view.findViewById(R.id.logoutButton);

        usernameTextView = view.findViewById(R.id.usernameTextView);
        ageTextView = view.findViewById(R.id.ageTextView);
        heightTextView = view.findViewById(R.id.heightTextView);
        weightTextView = view.findViewById(R.id.weightTextView);
        genderTextView = view.findViewById(R.id.genderTextView);
        activityLevelTextView = view.findViewById(R.id.activityLevelTextView);


        SharedPreferences sharedPreferences =getActivity().getSharedPreferences(getActivity().getPackageName(), MODE_PRIVATE);
        String username = sharedPreferences.getString("User", "");

        System.out.println("username: " + username);

        UserEntity user = AppState.getInstance().getDb().userDao().findByUsername(username);

        usernameTextView.setText(user.getUsername());
        ageTextView.setText(String.valueOf(user.getAge()));
        heightTextView.setText(String.valueOf(user.getHeight()));
        weightTextView.setText(String.valueOf(user.getWeight()));
        genderTextView.setText(user.getGender());
        activityLevelTextView.setText(user.getActivityLevel());

        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), StatisticsActivity.class);
                startActivity(intent);
            }
        });
        editDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditDataActivity.class);
                startActivity(intent);
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(), MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("isLoggedIn");
                editor.apply();

                Intent intent = new Intent(getContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}

