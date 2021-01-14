package grp02.brg_app.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import grp02.brg_app.R;
import grp02.brg_app.Model.DTO_recipe;

public class HistoryAdapter extends BaseAdapter {
    Context mContext;
    List<DTO_recipe> recipes;

    public HistoryAdapter(Context context, List<DTO_recipe> gameLogs){
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DTO_recipe recipe = recipes.get(position);


        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.historik_card, null);
        }

        // 3
        final TextView title = convertView.findViewById(R.id.HC_titleTV);
        final TextView date = convertView.findViewById(R.id.HC_brewDateTV);
        final TextView hiddenId = convertView.findViewById(R.id.HC_hiddenID);
        final Button HC_brewBtn = convertView.findViewById(R.id.HC_brewBtn);
        final Button HC_setfavoriteBtn = convertView.findViewById(R.id.HC_setfavoriteBtn);

        String time = recipe.getDateTime();
        // 4

        final View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == HC_brewBtn){

                }
                if(v == HC_setfavoriteBtn){
                    System.out.println("test favorit"+recipe.getRecipeID());
                }
            }
        };
        HC_brewBtn.setOnClickListener(onClickListener);
        HC_setfavoriteBtn.setOnClickListener(onClickListener);

        title.setText(recipe.getRecipeName());
        date.setText(recipe.getDateTime());
        System.out.println(recipe.getDateTime());


        return convertView;
    }

}
