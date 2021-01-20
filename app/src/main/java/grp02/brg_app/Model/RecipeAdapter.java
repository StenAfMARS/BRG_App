package grp02.brg_app.Model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.util.List;

import grp02.brg_app.Control.DatabaseController;
import grp02.brg_app.R;
import grp02.brg_app.View.Fragments.OnPressedBryg;
import grp02.brg_app.View.HistorikActivity1;
import grp02.brg_app.View.HistorikFragments.CardInfo;
import grp02.brg_app.View.HistorikFragments.RecipeList;

public class RecipeAdapter extends BaseAdapter implements View.OnClickListener {
    FragmentActivity activity;
    List<DTO_recipe> recipes;
    DatabaseController dbControl = DatabaseController.getInstance();

    public RecipeAdapter(FragmentActivity activity, List<DTO_recipe> gameLogs){
        this.activity = activity;
        this.recipes = gameLogs;
    }

    @Override
    public int getCount() {
        return recipes.size();
    }

    @Override
    public DTO_recipe getItem(int position) {
        return recipes.get(recipes.size() - (position+1));
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DTO_recipe recipe = recipes.get(recipes.size() - (position+1));
        String recDate = recipe.getDateTime();
        String recTitel = recipe.getRecipeName();


        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.historik_card, null);
        }

        // 3
        TextView title = convertView.findViewById(R.id.HC_titleTV);
        TextView date = convertView.findViewById(R.id.HC_brewDateTV);
        Button HC_brewBtn = convertView.findViewById(R.id.HC_brewBtn);
        Button HC_setfavoriteBtn = convertView.findViewById(R.id.HC_setfavoriteBtn);
        Button HC_shareBtn = convertView.findViewById(R.id.HC_shareBtn);
        View HC_cardHeader = convertView.findViewById(R.id.hsCardHeader);

        title.setText(recTitel);
        date.setText(recDate);

        // Check if recipe is in favorites.
        if(dbControl.getDB().checkPreferencesForItem(recipe.getRecipeID())) {
            HC_setfavoriteBtn.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_star_24, 0);
        } else {
            HC_setfavoriteBtn.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_star_outline_24, 0);
        }

        HC_brewBtn.setOnClickListener(this);
        HC_setfavoriteBtn.setOnClickListener(this);
        HC_shareBtn.setOnClickListener(this);

        HC_shareBtn.setTag(recipe);
        HC_setfavoriteBtn.setTag(recipe);

        HC_cardHeader.setOnClickListener(this);
        title.setOnClickListener(this);
        date.setOnClickListener(this);

        HC_cardHeader.setTag(recipe.getRecipeID());
        title.setTag(recipe.getRecipeID());
        date.setTag(recipe.getRecipeID());


        return convertView;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.HC_brewBtn:
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.FLHistorikOpenCards, new OnPressedBryg(activity))
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.HC_setfavoriteBtn:
                Button changeIconState = view.findViewById(R.id.HC_setfavoriteBtn);

                DTO_recipe recipe = (DTO_recipe) view.getTag();

                RecipeList.toggleFavoritesBtn(recipe, changeIconState);

                break;
            case R.id.HC_shareBtn:
                DTO_recipe recipeObj = (DTO_recipe) view.getTag();
                Gson gson = new Gson();

                String shareBody = gson.toJson(recipeObj);

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, recipeObj.getRecipeName());
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                activity.startActivity(Intent.createChooser(sharingIntent, "Share via"));
                break;
            default:
                // der er trykket på selve kortet.
                int id = (Integer) view.getTag();
                // hsList.changeFragmentToCardInfo(id);
                // Show CardInfo
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);

                Fragment cardInfoFrag = new CardInfo();
                cardInfoFrag.setArguments(bundle);

                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.FLHistorikOpenCards, cardInfoFrag)
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}
