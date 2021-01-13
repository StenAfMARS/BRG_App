package grp02.brg_app.View.BrygFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.webianks.library.scroll_choice.ScrollChoice;

import java.util.ArrayList;
import java.util.List;

import grp02.brg_app.Control.RecipeFactoryController;
import grp02.brg_app.R;

public class BloomWater extends Fragment implements View.OnClickListener {


    List<String> mlVandTilBloom = new ArrayList<>();
    int mlVandTilBloomObjekt;

    TextView hvorMangeMlVand;
    ScrollChoice scrollChoice;
    Button buttonNext3, buttonTilbage3;
    ProgressBar progressBar;
    int progressBarStatus = 64;


    @Override
    public View onCreateView(LayoutInflater i,ViewGroup container,Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_hvor_meget_vand_til_bloom,container, false);

        progressBar = rod.findViewById(R.id.progressBar3);
        progressBar.setProgress(progressBarStatus);

        buttonNext3 = rod.findViewById(R.id.buttonNext3);
        buttonNext3.setText("NÆSTE");
        buttonNext3.setOnClickListener(this);

        buttonTilbage3 = rod.findViewById(R.id.buttonTilbage3);
        buttonTilbage3.setText("TILBAGE");
        buttonTilbage3.setOnClickListener(this);

        hvorMangeMlVand = rod.findViewById(R.id.textViewBloomTid);
        hvorMangeMlVand.setText("HVOR MEGET VAND SKAL DU BRUGE TIL BLOOM?");

        scrollChoice = rod.findViewById(R.id.scroll_choice_BloomTid);
        loadDeForskelligeMængder();

        mlVandTilBloomObjekt = 45;

        scrollChoice.addItems(mlVandTilBloom,15); //default index, så den er på "60"
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                mlVandTilBloomObjekt = Integer.parseInt(name.substring(0, name.length()-3));
                System.out.println("Ml vand til Bloom: "+mlVandTilBloomObjekt);
            }
        });

        return rod;
    }

    private void loadDeForskelligeMængder() {

        for(int i = 30; i <= 60; i++) {
            mlVandTilBloom.add(i + " ml");
        }

    }


    @Override
    public void onClick(View v) {
        if (v == buttonNext3){
            progressBarStatus += 16;
            progressBar.setProgress(progressBarStatus);

            RecipeFactoryController.getInstance().setBloomWater(mlVandTilBloomObjekt);
            BloomTime bloomvandDistribueringFragment = new BloomTime();
            getFragmentManager().beginTransaction()
                    //.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.broegFragmentetIActivity, bloomvandDistribueringFragment)
                    .addToBackStack(null)
                    .commit();}
        else if (v == buttonTilbage3){
            BrewingTemperature brewingTemperature = new BrewingTemperature();
            getFragmentManager().beginTransaction()
                    //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
                    .replace(R.id.broegFragmentetIActivity, brewingTemperature)
                    .addToBackStack(null)
                    .commit();
        }
    }
}

