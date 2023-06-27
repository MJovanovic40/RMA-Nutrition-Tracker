package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments;

import android.app.Activity;
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

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activityAddFood.AddFoodActivity;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments.adapter.MealAdapter;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments.models.Obrok;

public class PlanFragment extends Fragment {

    private FloatingActionButton floatingActionButton;
    private MealAdapter adapter;
    private Button posaljiButton;

    private Map<Integer, List<Obrok>> meals;
    private Map<Integer, MealAdapter> mealAdapterMap;

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

        meals = new HashMap<>();
        meals.put(1, new ArrayList<>());
        meals.put(2, new ArrayList<>());
        meals.put(3, new ArrayList<>());
        meals.put(4, new ArrayList<>());
        meals.put(5, new ArrayList<>());
        meals.put(6, new ArrayList<>());
        meals.put(7, new ArrayList<>());

        ListView listView1 = view.findViewById(R.id.listView1);
        ListView listView2 = view.findViewById(R.id.listView2);
        ListView listView3 = view.findViewById(R.id.listView3);
        ListView listView4 = view.findViewById(R.id.listView4);
        ListView listView5 = view.findViewById(R.id.listView5);
        ListView listView6 = view.findViewById(R.id.listView6);
        ListView listView7 = view.findViewById(R.id.listView7);

        //adapter = new MealAdapter(requireContext(), meals);

        mealAdapterMap = new HashMap<>();
        mealAdapterMap.put(1, new MealAdapter(getContext(), meals.get(1)));
        mealAdapterMap.put(2, new MealAdapter(getContext(), meals.get(2)));
        mealAdapterMap.put(3, new MealAdapter(getContext(), meals.get(3)));
        mealAdapterMap.put(4, new MealAdapter(getContext(), meals.get(4)));
        mealAdapterMap.put(5, new MealAdapter(getContext(), meals.get(5)));
        mealAdapterMap.put(6, new MealAdapter(getContext(), meals.get(6)));
        mealAdapterMap.put(7, new MealAdapter(getContext(), meals.get(7)));

        listView1.setAdapter(mealAdapterMap.get(1));
        listView2.setAdapter(mealAdapterMap.get(2));
        listView3.setAdapter(mealAdapterMap.get(3));
        listView4.setAdapter(mealAdapterMap.get(4));
        listView5.setAdapter(mealAdapterMap.get(5));
        listView6.setAdapter(mealAdapterMap.get(6));
        listView7.setAdapter(mealAdapterMap.get(7));

        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        Calendar date = Calendar.getInstance();
                        date.setTime(new Date(data.getLongExtra("date", 0)));

                        String category = data.getStringExtra("category");
                        String foodName = data.getStringExtra("foodName");
                        float calories = data.getFloatExtra("calories", 0);

                        int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);

                        if(dayOfWeek == 1) {
                            dayOfWeek = 7;
                        } else {
                            dayOfWeek--;
                        }
                        //mealAdapterMap.get(dayOfWeek).clear();
                        meals.get(dayOfWeek).add(new Obrok(foodName, category, String.valueOf(calories)));
                        System.out.println(dayOfWeek);
                        /*for(Obrok obrok: meals.get(dayOfWeek)) {
                            System.out.println(obrok);
                            mealAdapterMap.get(dayOfWeek).insert(obrok, mealAdapterMap.get(dayOfWeek).getCount());
                        }*/
                        mealAdapterMap.get(dayOfWeek).notifyDataSetChanged();

                    }
                });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddFoodActivity.class);
                someActivityResultLauncher.launch(intent);
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

    /*private List<Obrok> generateDummyData() {
        List<Obrok> obroci = new ArrayList<>();
        obroci.add(new Obrok("Ime 1", "Kategorija 1", "Kalorije 1"));
        obroci.add(new Obrok("Ime 2", "Kategorija 2", "Kalorije 2"));
        obroci.add(new Obrok("Ime 3", "Kategorija 3", "Kalorije 3"));
        return obroci;
    }*/

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
        //List<Obrok> obroci = generateDummyData();
        /*for (Obrok obrok : obroci) {
            sb.append("Ime: ").append(obrok.getIme()).append("\n");
            sb.append("Kategorija: ").append(obrok.getKategorija()).append("\n");
            sb.append("Kalorije: ").append(obrok.getKalorije()).append("\n\n");
        }*/

        for(int i = 1; i <= 7; i++){
            sb.append(getDayFromInt(i)).append("\n").append("----------------------------").append("\n");
            for(Obrok obrok: meals.get(i)) {
                sb.append("Ime: ").append(obrok.getIme()).append("\n");
                sb.append("Kategorija: ").append(obrok.getKategorija()).append("\n");
                sb.append("Kalorije: ").append(obrok.getKalorije()).append("\n\n");
            }

        }
        return sb.toString();
    }

    private String getDayFromInt(int num) {
        switch (num) {
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 7:
                return "Saturday";
        }
        return null;
    }

}
