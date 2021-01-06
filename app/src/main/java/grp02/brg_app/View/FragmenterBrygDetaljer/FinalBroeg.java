package grp02.brg_app.View.FragmenterBrygDetaljer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import grp02.brg_app.Control.RecipeFactory;
import grp02.brg_app.Model.BrygObjekt;
import grp02.brg_app.Model.DTO_recipe;
import grp02.brg_app.R;
import grp02.brg_app.View.BroegActivity1;
import grp02.brg_app.View.HistorikActivity1;
import grp02.brg_app.View.MainActivity;

public class FinalBroeg extends Fragment implements View.OnClickListener {

    String navn;
    double gramKaffe;
    int mlVand, antalSekunder, antalSekunderTilBloomVandDistribuering, mlVandTilBloom;
    TextView textView, navnet, gramKaffe1, mlVand2, antalSekunder3, mlVandTilBloom4, antalSekunderTilBloomVandDistribuering5;
    Button btnBrewAction, btnSaveBrew;
    ProgressBar progressBar;
    int progressBarStatus = 100;
    RecipeFactory recipeFactory = RecipeFactory.getInstance();

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container,
                             Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_final_broeg, container, false);

        navnet = rod.findViewById(R.id.navn);
        gramKaffe1 = rod.findViewById(R.id.gramKaffe1);
        mlVand2 = rod.findViewById(R.id.mlVand2);
        antalSekunder3 = rod.findViewById(R.id.antalSekunder3);
        mlVandTilBloom4 = rod.findViewById(R.id.mlVandTilBloom4);
        antalSekunderTilBloomVandDistribuering5 = rod.findViewById(R.id.antalSekunderTilBloomVandDistribuering5);

        navnet.setText(navn);
        gramKaffe1.setText(gramKaffe + " g. kaffe");
        mlVand2.setText(mlVand + " ml. vand");
        antalSekunder3.setText(antalSekunder + " sekunder");
        mlVandTilBloom4.setText(mlVandTilBloom + " ml. vand til bloom");
        antalSekunderTilBloomVandDistribuering5.setText(antalSekunderTilBloomVandDistribuering + " sekunder til bloom vandsdistribuering");

        btnBrewAction = rod.findViewById(R.id.FB_brewActionBtn);
        btnBrewAction.setOnClickListener(this);

        btnSaveBrew = rod.findViewById(R.id.FB_saveBrewBtn);
        btnSaveBrew.setOnClickListener(this);

        return rod;
    }


    @Override
    public void onClick(View v) {
        if (v == btnBrewAction) {
            BroegActivity1 broegActivity1 = new BroegActivity1();

            broegActivity1.list.add(new BrygObjekt(navn, gramKaffe, mlVand, antalSekunder, mlVandTilBloom, antalSekunderTilBloomVandDistribuering));

            //TODO
            // Save Recipe
            // Brew

        } else if (v == btnSaveBrew) {
            BroegActivity1 broegActivity1 = new BroegActivity1();
            broegActivity1.list.add(new BrygObjekt(navn, gramKaffe, mlVand, antalSekunder, mlVandTilBloom, antalSekunderTilBloomVandDistribuering));

            //TODO
            // Save recipe
        }
    }
}