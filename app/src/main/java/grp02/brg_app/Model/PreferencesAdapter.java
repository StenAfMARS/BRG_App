package grp02.brg_app.Model;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import grp02.brg_app.Control.DatabaseController;
import grp02.brg_app.Control.StorageController;
import grp02.brg_app.R;
import grp02.brg_app.Model.DTO_recipe;
import grp02.brg_app.View.Fragments.OnPressedBryg;
import grp02.brg_app.View.MainActivity;

public class PreferencesAdapter extends BaseAdapter {
    Context mContext;
    List<DTO_recipe> recipes;
    StorageController storageController;

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

        DTO_recipe recipe = recipes.get(position);


        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.historik_card, null);
        }

        // 3
        final TextView title = convertView.findViewById(R.id.HC_titleTV);
        final Button HC_brewBtn = convertView.findViewById(R.id.HC_brewBtn);
        final Button HC_setfavoriteBtn = convertView.findViewById(R.id.HC_setfavoriteBtn);
        final FrameLayout ShowBrewAnimation = convertView.findViewById(R.id.ShowBrewAnimation);
        // 4
        HC_setfavoriteBtn.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_star_24, 0);
        final View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == HC_brewBtn){
                    MainActivity mainActivity = new MainActivity();
                    mainActivity.getFragment();

                }
                if(v == HC_setfavoriteBtn){
                    System.out.println(recipe.getRecipeID());
                    DatabaseController.getInstance().getDB().deleteRecipe("Preferences","fk_RecipeID",recipe.getRecipeID());
                    HC_setfavoriteBtn.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_star_outline_24, 0);
                        System.out.println("row add");

                }
            }
        };
        HC_brewBtn.setOnClickListener(onClickListener);
        HC_setfavoriteBtn.setOnClickListener(onClickListener);

        title.setText(recipe.getRecipeName());
        System.out.println(recipe.getDateTime());


        return convertView;
    }

}
