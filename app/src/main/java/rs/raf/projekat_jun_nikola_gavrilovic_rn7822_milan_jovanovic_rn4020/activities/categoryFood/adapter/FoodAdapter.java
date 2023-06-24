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
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments.models.Category;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.enteties.Food;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<Food> foodList;
    private List<Food> filteredList;
    private OnFoodClickListener onFoodClickListener;

    public interface OnFoodClickListener {
        void onFoodClick(Food food);
    }

    public FoodAdapter(List<Food> foodList, OnFoodClickListener onFoodClickListener) {
        this.foodList = foodList;
        this.filteredList = new ArrayList<>(foodList);
        this.onFoodClickListener = onFoodClickListener;
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
        holder.bind(food, onFoodClickListener);
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void setItems(List<Food> foodList) {
        filteredList.clear();
        filteredList.addAll(foodList);
        notifyDataSetChanged();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView descriptionTextView;
        private TextView caloriesTextView;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            caloriesTextView = itemView.findViewById(R.id.caloriesTextView);
        }

        public void bind(Food food, OnFoodClickListener onFoodClickListener) {
            nameTextView.setText(food.getIme());
            descriptionTextView.setText(food.getOpis());
            String calories = food.getCalories() > 0 ? String.valueOf(food.getCalories()) : "Cals";
            caloriesTextView.setText(calories);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onFoodClickListener.onFoodClick(food);
                    }
                }
            });
        }
    }
}
