package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments.models.Obrok;

public class MealAdapter extends ArrayAdapter<Obrok> {

    public MealAdapter(Context context, List<Obrok> obroci) {
        super(context, 0, obroci);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_obrok, parent, false);
        }

        Obrok obrok = getItem(position);
        if (obrok != null) {
            TextView imeTextView = view.findViewById(R.id.imeTextView);
            TextView kategorijaTextView = view.findViewById(R.id.kategorijaTextView);
            TextView kalorijeTextView = view.findViewById(R.id.kalorijeTextView);

            imeTextView.setText(obrok.getIme());
            kategorijaTextView.setText(obrok.getKategorija());
            kalorijeTextView.setText(obrok.getKalorije());
        }

        return view;
    }
}
