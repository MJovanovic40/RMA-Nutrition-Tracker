package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments.models.Category;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments.models.CategoryAdapter;

public class HomeFragment extends Fragment {
    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        List<Category> categoryList = createDummyCategories();
        categoryAdapter = new CategoryAdapter(categoryList, new CategoryAdapter.OnCategoryClickListener() {
            @Override
            public void onCategoryClick(Category category) {
                Toast.makeText(requireContext(), "Kliknuli ste na kategoriju: " + category.getNazivKategorije(), Toast.LENGTH_SHORT).show();
            }

            public void onCategoryOptionsClick(Category category) {
                Toast.makeText(requireContext(), "Kliknuli ste na opcije za kategoriju: " + category.getNazivKategorije(), Toast.LENGTH_SHORT).show();
            }
        });

        categoryRecyclerView.setAdapter(categoryAdapter);

        return view;
    }

    private List<Category> createDummyCategories() {
        List<Category> kategorije = new ArrayList<>();
        kategorije.add(new Category(null, "naziv1", "opis1"));
        kategorije.add(new Category(null, "naziv2", "opis2"));
        kategorije.add(new Category(null, "naziv3", "opis3"));

        return kategorije;
    }

}