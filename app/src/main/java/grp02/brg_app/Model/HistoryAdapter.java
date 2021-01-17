package grp02.brg_app.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

import grp02.brg_app.Control.DatabaseController;
import grp02.brg_app.R;

public class HistoryAdapter extends BaseAdapter implements View.OnClickListener {
    Context mContext;
    List<DTO_recipe> recipes;
    DTO_recipe recipe;

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

        recipe = recipes.get(position);
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

        HC_brewBtn.setOnClickListener(this);
        HC_setfavoriteBtn.setOnClickListener(this);
        HC_cardHeader.setOnClickListener(this);


        return convertView;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.HC_brewBtn:
                break;
            case R.id.HC_setfavoriteBtn:
                break;
            default:
                // der er trykket på selve kortet.
                break;
        }
/*
        if(view == HC_cardHeader) {
            System.out.println("YO MAMMA!!!!!! ");
        }

        if(view == HC_brewBtn){

        }

        if(view == HC_setfavoriteBtn){
            System.out.println(recipe.getRecipeID());
            DatabaseController.getInstance().getDB().addRow("Preferences", recipe.getRecipeID(),false,"");

            HC_setfavoriteBtn.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_star_24, 0);
            System.out.println("row add");
        }
*/
    }
}
