package grp02.brg_app.View.HistorikFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import grp02.brg_app.Control.LogicController;
import grp02.brg_app.Control.RecipeFactoryController;
import grp02.brg_app.Control.StorageController;
import grp02.brg_app.R;
import grp02.brg_app.View.Fragments.OnSaveBryg;
import grp02.brg_app.View.HistorikActivity1;

public class CardInfo extends Fragment implements View.OnClickListener {

    private HistorikActivity1 historikActivity1 = HistorikActivity1.getInstance();
    private Button backToHistoryBtn, beginBrewBtn, saveBrewBtn;
    private StorageController storageController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        storageController = new StorageController(HistorikActivity1.context);

        View cardInfoView = inflater.inflate(R.layout.fragment_card_info, container, false);

        backToHistoryBtn = cardInfoView.findViewById(R.id.FCI_backToHistoryBtn);
        beginBrewBtn = cardInfoView.findViewById(R.id.FCI_BeginBrewBtn);
        saveBrewBtn = cardInfoView.findViewById(R.id.FCI_SaveBrewBtn);
        backToHistoryBtn.setOnClickListener(this);
        beginBrewBtn.setOnClickListener(this);
        saveBrewBtn.setOnClickListener(this);

        return cardInfoView;
    }

    @Override
    public void onClick(View view) {
        if(view == backToHistoryBtn) {

        }

        if(view == beginBrewBtn) {

            // Get info from Database

            // Show new Fragment and Start Brew

            getFragmentManager().beginTransaction()
                    //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.broegFragmentetIActivity, new OnSaveBryg())
                    .addToBackStack(null)
                    .commit();

        }

        if(view == saveBrewBtn) {

        }
    }
}