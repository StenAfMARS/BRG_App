package grp02.brg_app.View.HistorikFragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.google.android.material.card.MaterialCardView;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

import grp02.brg_app.Control.DatabaseController;
import grp02.brg_app.Control.IDatabaseConnector;
import grp02.brg_app.Model.DTO_recipe;
import grp02.brg_app.Model.HistoryAdapter;
import grp02.brg_app.R;
import grp02.brg_app.View.Fragments.OnSaveBryg;
import grp02.brg_app.View.Fragments.WaterCoffeeRatio;
import grp02.brg_app.View.HistorikActivity1;


public class HistorikList extends Fragment {

    private View historikListView;
    private Context context = HistorikActivity1.context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        historikListView = inflater.inflate(R.layout.fragment_historik_list, container, false);

        initHistoryList(DatabaseController.getInstance().getDB());

        return historikListView;
    }

    private void initHistoryList(IDatabaseConnector db) {
        final ListView listView = historikListView.findViewById(R.id.historyCardList);

        HistoryAdapter adapter = new HistoryAdapter(context, db.getHistory());
        listView.setAdapter(adapter);

/*
        listView.setOnItemClickListener((parent, view, position, id) -> {
            recipe = adapter.getItem(position);
            int recipeID = recipe.getRecipeID();

            System.out.println(recipe.getRecipeName());
        });
*/

    }
}