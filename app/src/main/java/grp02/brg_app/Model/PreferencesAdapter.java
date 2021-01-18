package grp02.brg_app.Model;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.util.List;

import grp02.brg_app.Control.DatabaseController;
import grp02.brg_app.Control.IDatabaseConnector;
import grp02.brg_app.Control.StorageController;
import grp02.brg_app.R;
import grp02.brg_app.Model.DTO_recipe;
import grp02.brg_app.View.Fragments.OnPressedBryg;
import grp02.brg_app.View.HistorikActivity1;
import grp02.brg_app.View.HistorikFragments.CardInfo;
import grp02.brg_app.View.MainActivity;

public class PreferencesAdapter extends BaseAdapter implements View.OnClickListener {
    Context mContext;
    List<DTO_recipe> recipes;
    DTO_recipe recipe;

    public PreferencesAdapter(Context context, List<DTO_recipe> gameLogs){
        mContext = context;
        this.recipes = gameLogs;
    }

    @Override
    public int getCount() {
        return recipes.size();
    }

    @Override
    public Object getItem(int position) {
        return recipes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    boolean toogleFavorit = false;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        recipe = recipes.get(position);


        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.historik_card, null);
        }

        // 3
        TextView title = convertView.findViewById(R.id.HC_titleTV);
        Button HC_brewBtn = convertView.findViewById(R.id.HC_brewBtn);
        Button HC_setfavoriteBtn = convertView.findViewById(R.id.HC_setfavoriteBtn);
        Button HC_shareBtn = convertView.findViewById(R.id.HC_shareBtn);
        View HC_cardHeader = convertView.findViewById(R.id.hsCardHeader);
        // 4
        HC_setfavoriteBtn.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_star_24, 0);

        title.setText(recipe.getRecipeName());

        HC_brewBtn.setOnClickListener(this);
        HC_setfavoriteBtn.setOnClickListener(this);
        HC_cardHeader.setOnClickListener(this);
        HC_shareBtn.setOnClickListener(this);
        HC_shareBtn.setTag(recipe);
        HC_cardHeader.setTag(recipe.getRecipeID());

        return convertView;
    }

    @Override
    public void onClick(View v) {
        Button setFavoriteBtn = v.findViewById(R.id.HC_setfavoriteBtn);

        switch (v.getId()){
            case R.id.HC_shareBtn:
                DTO_recipe recipeObj = (DTO_recipe) v.getTag();
                Gson gson = new Gson();

                String shareBody = gson.toJson(recipeObj);

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, recipeObj.getRecipeName());
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                ((MainActivity)mContext).startActivity(Intent.createChooser(sharingIntent, "Share via"));
                break;
            case R.id.HC_brewBtn:
                MainActivity mainActivity = new MainActivity();
                mainActivity.getFragment();
                break;
            case R.id.HC_setfavoriteBtn:
                DatabaseController.getInstance().getDB().deleteRecipe("Preferences","fk_RecipeID",recipe.getRecipeID());
                setFavoriteBtn.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_star_outline_24, 0);
                setFavoriteBtn.setVisibility(View.GONE);
                break;
            default:
                int id = (Integer) v.getTag();
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                bundle.putBoolean("pref", true);

                Fragment cardInfoFrag = new CardInfo();
                cardInfoFrag.setArguments(bundle);

                ListView preferencesLV = ((MainActivity)mContext).findViewById(R.id.PreferencesCardList);
                preferencesLV.setVisibility(View.GONE);
                cardInfoFrag.setArguments(bundle);
                ((MainActivity)mContext).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.ShowBrewAnimation, cardInfoFrag)
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}
