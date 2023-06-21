package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.category;

import java.util.List;

public class CategoryResponseWrapper {
    private List<CategoryResponse> categories;

    public CategoryResponseWrapper() {
    }


    public List<CategoryResponse> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryResponse> categories) {
        this.categories = categories;
    }
}
