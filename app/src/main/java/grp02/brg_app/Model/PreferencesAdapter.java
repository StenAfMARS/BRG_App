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
        final TextView title = convertView.findViewById(R.id.HC_titleTV);
        final Button HC_brewBtn = convertView.findViewById(R.id.HC_brewBtn);
        final Button HC_setfavoriteBtn = convertView.findViewById(R.id.HC_setfavoriteBtn);
        final View HC_cardHeader = convertView.findViewById(R.id.hsCardHeader);
        // 4
        HC_setfavoriteBtn.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_star_24, 0);

        title.setText(recipe.getRecipeName());

        HC_brewBtn.setOnClickListener(this);
        HC_setfavoriteBtn.setOnClickListener(this);
        HC_cardHeader.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        Button setFavoriteBtn = v.findViewById(R.id.HC_setfavoriteBtn);

        switch (v.getId()){
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
                break;
        }
    }
}
