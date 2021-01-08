package grp02.brg_app.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import grp02.brg_app.R;
import grp02.brg_app.Model.DTO_recipe;

public class HistoryAdapter extends BaseAdapter {
    Context mContext;
    DTO_recipe[] recipes;

    public HistoryAdapter(Context context, DTO_recipe[] gameLogs){
        mContext = context;
        this.recipes = gameLogs;
    }

    @Override
    public int getCount() {
        return recipes.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DTO_recipe recipe = recipes[position];

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.historik_card, null);
        }

        // 3
        final TextView title = convertView.findViewById(R.id.HC_titleTV);
        final TextView date = convertView.findViewById(R.id.HC_brewDateTV);

        // 4
        title.setText(recipe.getRecipeName());
        title.setText("");

        return convertView;
    }
}
