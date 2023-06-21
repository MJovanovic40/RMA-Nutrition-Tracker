package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.enteties.Food;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<Food> foodList;
    private List<Food> filteredList; // Nova lista za filtrirane stavke

    public FoodAdapter(List<Food> foodList) {
        this.foodList = foodList;
        this.filteredList = new ArrayList<>(foodList); // Kopiranje svih stavki u filtriranu listu
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = filteredList.get(position);
        holder.bind(food);
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void setItems(List<Food> foodList) {
        filteredList.clear(); // Očistite filtriranu listu
        filteredList.addAll(foodList); // Dodajte nove stavke u filtriranu listu
        notifyDataSetChanged(); // Osvežite prikaz
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView descriptionTextView;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }

        public void bind(Food food) {
            nameTextView.setText(food.getIme());
            descriptionTextView.setText(food.getOpis());
        }
    }
}
