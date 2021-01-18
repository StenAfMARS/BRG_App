package grp02.brg_app.Model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.DataShareWriteAdapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import grp02.brg_app.Control.DatabaseController;
import grp02.brg_app.R;
import grp02.brg_app.View.Fragments.GrindSize;
import grp02.brg_app.View.HistorikActivity1;
import grp02.brg_app.View.HistorikFragments.CardInfo;
import grp02.brg_app.View.HistorikFragments.HistorikList;

public class HistoryAdapter extends BaseAdapter implements View.OnClickListener {
    Context mContext;
    List<DTO_recipe> recipes;
    DatabaseController dbControl = DatabaseController.getInstance();
    HistorikList hsList = HistorikList.getInstance();
    HistorikActivity1 hsAct = HistorikActivity1.getInstance();

    public HistoryAdapter(Context context, List<DTO_recipe> gameLogs){
        mContext = context;
        this.recipes = gameLogs;
    }

    @Override
    public int getCount() {
        return recipes.size();
    }

    @Override
    public DTO_recipe getItem(int position) {
        return recipes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DTO_recipe recipe = recipes.get(position);
        String recId = String.valueOf(recipe.getRecipeID());
        String recDate = recipe.getDateTime();
        String recTitel = recipe.getRecipeName();


        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.historik_card, null);
        }

        // 3
        TextView title = convertView.findViewById(R.id.HC_titleTV);
        TextView date = convertView.findViewById(R.id.HC_brewDateTV);
        TextView id = convertView.findViewById(R.id.HC_cardID);
        Button HC_brewBtn = convertView.findViewById(R.id.HC_brewBtn);
        Button HC_setfavoriteBtn = convertView.findViewById(R.id.HC_setfavoriteBtn);
        View HC_cardHeader = convertView.findViewById(R.id.hsCardHeader);

        title.setText(recTitel);
        date.setText(recDate);
        id.setText(recId);

        // Check if recipe is in favorites.
        if(dbControl.getDB().checkPreferencesForItem(recipe.getRecipeID())) {
            HC_setfavoriteBtn.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_star_24, 0);
        } else {
            HC_setfavoriteBtn.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_star_outline_24, 0);
        }

        HC_brewBtn.setOnClickListener(this);
        HC_setfavoriteBtn.setOnClickListener(this);
        HC_cardHeader.setOnClickListener(this);
        HC_setfavoriteBtn.setTag(recipe);
        HC_cardHeader.setTag(recipe.getRecipeID());

        return convertView;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.HC_brewBtn:
                break;
            case R.id.HC_setfavoriteBtn:
                Button changeIconState = view.findViewById(R.id.HC_setfavoriteBtn);

                DTO_recipe recipe = (DTO_recipe) view.getTag();

                hsList.toggleFavoritesBtn(recipe, changeIconState);

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

                ((HistorikActivity1)mContext).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.FLHistorikOpenCards, cardInfoFrag)
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}
