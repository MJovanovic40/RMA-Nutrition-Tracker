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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.AppState;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activitySavedFoodEdit.SavedFoodEditActivity;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood.adapter.FoodAdapter;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.database.entities.MealEntity;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood.models.Food;

public class MenuFragment extends Fragment implements FoodAdapter.OnFoodClickListener {
    private RecyclerView recyclerViewMenu;
    private FoodAdapter foodAdapter;
    private MenuViewModel menuViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        menuViewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        menuViewModel.getFoodListLiveData().observe(this, foodList -> {
            foodAdapter.setItems(foodList);
            foodAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        TextView textViewMenu = view.findViewById(R.id.textViewMenu);
        recyclerViewMenu = view.findViewById(R.id.recyclerViewMenu);

        textViewMenu.setGravity(View.TEXT_ALIGNMENT_CENTER);

        recyclerViewMenu.setLayoutManager(new LinearLayoutManager(requireContext()));

        foodAdapter = new FoodAdapter(new ArrayList<>(), this);
        recyclerViewMenu.setAdapter(foodAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        menuViewModel.loadMenuItems();
    }

    @Override
    public void onFoodClick(Food food) {
        Intent intent = new Intent(requireContext(), SavedFoodEditActivity.class);
        intent.putExtra("menuItem", food.getIme());
        intent.putExtra("menuFoodId", food.getId());
        startActivity(intent);
    }
}
