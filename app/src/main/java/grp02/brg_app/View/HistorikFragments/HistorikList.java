package grp02.brg_app.View.HistorikFragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

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
    DatabaseController dbControl = DatabaseController.getInstance();

    public static HistorikList Instance;

    public static HistorikList getInstance() {
        if(Instance == null) {
            Instance = new HistorikList();
        }

        return Instance;
    }

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
    }

    public void toggleFavoritesBtn(DTO_recipe recipe, Button currentBtn) {

        // Check if Id is in preferences
        if(dbControl.getDB().checkPreferencesForItem(recipe.getRecipeID())) {
            // If true -> remove it
            DatabaseController.getInstance().getDB().deleteRecipe("Preferences", "fk_RecipeID", recipe.getRecipeID());
            System.out.println("DELETE: " + recipe.getRecipeID());
            toggleVisuals(true, currentBtn);
        } else {
            // if False -> Add it
            DatabaseController.getInstance().getDB().addRow("Preferences", recipe.getRecipeID(),false,"");
            System.out.println("ADD: " + recipe.getRecipeID());
            toggleVisuals(false, currentBtn);
        }
    }

    public void toggleVisuals(boolean toggle, Button currentBtn) {
        if(toggle) {
            currentBtn.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_star_outline_24, 0);
        } else {
            currentBtn.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_star_24, 0);
        }
    }
}