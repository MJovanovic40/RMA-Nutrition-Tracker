package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activitySavedFoodEdit.SavedFoodEditActivity;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood.adapter.FoodAdapter;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.enteties.Food;

public class MenuFragment extends Fragment implements FoodAdapter.OnFoodClickListener {
    private RecyclerView recyclerViewMenu;
    private FoodAdapter foodAdapter;
    private List<Food> foodList;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        TextView textViewMenu = view.findViewById(R.id.textViewMenu);
        recyclerViewMenu = view.findViewById(R.id.recyclerViewMenu);

        // Set the text centered
        textViewMenu.setGravity(View.TEXT_ALIGNMENT_CENTER);

        // Initialize the RecyclerView
        recyclerViewMenu.setLayoutManager(new LinearLayoutManager(requireContext()));
        foodList = getMenuItems();
        foodAdapter = new FoodAdapter(foodList, this);
        recyclerViewMenu.setAdapter(foodAdapter);

        return view;
    }

    @Override
    public void onFoodClick(Food food) {
        Intent intent = new Intent(requireContext(), SavedFoodEditActivity.class);
        intent.putExtra("menuItem", food.getIme());
        startActivity(intent);
    }

    private List<Food> getMenuItems() {
        List<Food> menuItems = new ArrayList<>();
        menuItems.add(new Food("Ime", "NekaSlika"));
        menuItems.add(new Food("Ime23", "NekaSlika23"));
        menuItems.add(new Food("Ime44", "NekaSlika44"));
        return menuItems;
    }
}
