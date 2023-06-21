package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood.CategoryFoodActivity;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments.models.Category;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments.models.CategoryAdapter;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.CategoryResponse;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.CategoryResponseWrapper;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.providers.MealProvider;

public class HomeFragment extends Fragment {
    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;

    private ProgressBar categoryProgressBar;
    private MealProvider mealProvider;


    private void updateAdapter(List<Category> categories){
        categoryAdapter = new CategoryAdapter(categories, new CategoryAdapter.OnCategoryClickListener() {
            @Override
            public void onCategoryClick(Category category) {
                Intent intent = new Intent(requireContext(), CategoryFoodActivity.class);
                intent.putExtra("categoryName", category.getNazivKategorije());
                startActivity(intent);
            }

            public void onCategoryOptionsClick(Category category) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Opis kategorije");
                builder.setMessage(category.getOpisKategorije());
                builder.setPositiveButton("OK", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        categoryRecyclerView.setAdapter(categoryAdapter);

        categoryRecyclerView.setVisibility(View.VISIBLE);
        categoryProgressBar.setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        categoryProgressBar = view.findViewById(R.id.categoryProgressBar);

        List<Category> categoryList = createDummyCategories();
        categoryAdapter = new CategoryAdapter(categoryList, new CategoryAdapter.OnCategoryClickListener() {
            @Override
            public void onCategoryClick(Category category) {
                Intent intent = new Intent(requireContext(), CategoryFoodActivity.class);
                intent.putExtra("categoryName", category.getNazivKategorije());
                startActivity(intent);
            }

            public void onCategoryOptionsClick(Category category) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Opis kategorije");
                builder.setMessage(category.getOpisKategorije());
                builder.setPositiveButton("OK", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        categoryRecyclerView.setAdapter(categoryAdapter);

        //Fetch categories
        mealProvider = new MealProvider();

        mealProvider.getCategoryService().fetchAllCategories().enqueue(new Callback<CategoryResponseWrapper>() {
            @Override
            public void onResponse(Call<CategoryResponseWrapper> call, Response<CategoryResponseWrapper> response) {
                CategoryResponseWrapper categoryResponses = response.body();
                System.out.println(categoryResponses.getCategories());
                if(categoryResponses == null){
                    return;
                }

                int len = categoryResponses.getCategories().size();

                List<Category> categories = new ArrayList<>();

                for(CategoryResponse c: categoryResponses.getCategories()) {
                    URL url;
                    try {
                        url = new URL(c.getStrCategoryThumb());
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }

                    Category category;
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Category category = new Category(BitmapFactory.decodeStream(url.openConnection().getInputStream()), c.getStrCategory(), c.getStrCategoryDescription());
                                categories.add(category);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                    thread.start();
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                updateAdapter(categories);
            }

            @Override
            public void onFailure(Call<CategoryResponseWrapper> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });


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