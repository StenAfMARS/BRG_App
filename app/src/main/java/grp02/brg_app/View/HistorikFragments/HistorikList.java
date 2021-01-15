package grp02.brg_app.View.HistorikFragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import grp02.brg_app.Control.DatabaseController;
import grp02.brg_app.Control.IDatabaseConnector;
import grp02.brg_app.Model.HistoryAdapter;
import grp02.brg_app.R;
import grp02.brg_app.View.HistorikActivity1;


public class HistorikList extends Fragment implements View.OnClickListener {

    View historikListView;
    private Context context = HistorikActivity1.context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        historikListView = inflater.inflate(R.layout.fragment_historik_list, container, false);

        InitHistoryList(DatabaseController.getInstance().getDB());
        return historikListView;
    }

    private void InitHistoryList(IDatabaseConnector db) {
        ListView listView = historikListView.findViewById(R.id.historyCardList);

        HistoryAdapter adapter = new HistoryAdapter(context, db.getHistory());
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

    }
}