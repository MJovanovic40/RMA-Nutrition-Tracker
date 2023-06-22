package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> listaCategory;
    private OnCategoryClickListener onCategoryClickListener;

    public interface OnCategoryClickListener {
        void onCategoryClick(Category category);

        void onCategoryOptionsClick(Category category);
    }

    public CategoryAdapter(List<Category> listaCategory, OnCategoryClickListener onCategoryClickListener) {
        this.listaCategory = listaCategory;
        this.onCategoryClickListener = onCategoryClickListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kategorija, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = listaCategory.get(position);
        holder.bind(category);
    }

    @Override
    public int getItemCount() {
        return listaCategory.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private ImageView slikaKategorije;
        private TextView nazivKategorije;
        private ImageView categoryOptionsImageView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            slikaKategorije = itemView.findViewById(R.id.categoryImageView);
            nazivKategorije = itemView.findViewById(R.id.categoryNameTextView);
            categoryOptionsImageView = itemView.findViewById(R.id.categoryOptionsImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Category category = listaCategory.get(position);
                        onCategoryClickListener.onCategoryClick(category);
                    }
                }
            });

            categoryOptionsImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Category category = listaCategory.get(position);
                        onCategoryClickListener.onCategoryOptionsClick(category);
                    }
                }
            });
        }

        public void bind(Category category) {
            //slikaKategorije.setImageBitmap(category.getImage());
            Picasso.get().load(category.getImage()).into(slikaKategorije);
            nazivKategorije.setText(category.getNazivKategorije());
        }
    }
}

