package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activityAddFood.AddFoodActivity;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments.adapter.MealAdapter;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments.models.Obrok;

public class PlanFragment extends Fragment {

    private FloatingActionButton floatingActionButton;
    private MealAdapter adapter;
    private Button posaljiButton;

    public PlanFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        floatingActionButton = view.findViewById(R.id.fab);
        posaljiButton = view.findViewById(R.id.posaljiButton);

        ListView listView1 = view.findViewById(R.id.listView1);
        ListView listView2 = view.findViewById(R.id.listView2);
        ListView listView3 = view.findViewById(R.id.listView3);
        ListView listView4 = view.findViewById(R.id.listView4);
        ListView listView5 = view.findViewById(R.id.listView5);
        ListView listView6 = view.findViewById(R.id.listView6);
        ListView listView7 = view.findViewById(R.id.listView7);

        adapter = new MealAdapter(requireContext(), generateDummyData());
        listView1.setAdapter(adapter);
        listView2.setAdapter(adapter);
        listView3.setAdapter(adapter);
        listView4.setAdapter(adapter);
        listView5.setAdapter(adapter);
        listView6.setAdapter(adapter);
        listView7.setAdapter(adapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddFoodActivity.class);
                startActivity(intent);
            }
        });

        posaljiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Unesite e-poštu");

                final EditText input = new EditText(requireContext());
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                builder.setView(input);

                builder.setPositiveButton("Potvrdi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String email = input.getText().toString().trim();
                        if (isValidEmail(email)) {
                            sendEmail(email);
                        } else {
                            Toast.makeText(requireContext(), "Unesite validnu e-poštu", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Otkaži", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });


    }

    private List<Obrok> generateDummyData() {
        List<Obrok> obroci = new ArrayList<>();
        obroci.add(new Obrok("Ime 1", "Kategorija 1", "Kalorije 1"));
        obroci.add(new Obrok("Ime 2", "Kategorija 2", "Kalorije 2"));
        obroci.add(new Obrok("Ime 3", "Kategorija 3", "Kalorije 3"));
        return obroci;
    }

    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void sendEmail(String email) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Plan ishrane");
        emailIntent.putExtra(Intent.EXTRA_TEXT, generateEmailContent());

        try {
            startActivity(Intent.createChooser(emailIntent, "Pošalji e-poštu..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(requireContext(), "Nema instalirane aplikacije za slanje e-pošte.", Toast.LENGTH_SHORT).show();
        }
    }

    private String generateEmailContent() {
        StringBuilder sb = new StringBuilder();
        List<Obrok> obroci = generateDummyData();
        for (Obrok obrok : obroci) {
            sb.append("Ime: ").append(obrok.getIme()).append("\n");
            sb.append("Kategorija: ").append(obrok.getKategorija()).append("\n");
            sb.append("Kalorije: ").append(obrok.getKalorije()).append("\n\n");
        }
        return sb.toString();
    }

}
