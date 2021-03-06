package grp02.brg_app.View.Fragments;

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

public class WaterCoffeeRatio extends Fragment implements View.OnClickListener {


    private List<String> mlVandScroll = new ArrayList<>();
    private int mlVand;
    private TextView hvorMangeMlVand;
    private ScrollChoice scrollChoice;
    private Button buttonNext1, buttonTilbage1;
    private ProgressBar progressBar;
    private int progressBarStatus = 32;


    @Override
    public View onCreateView(LayoutInflater i,ViewGroup container,Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_hvor_meget_vand,container, false);

        progressBar = rod.findViewById(R.id.progressBar1);
        progressBar.setProgress(progressBarStatus);
        buttonNext1 = rod.findViewById(R.id.buttonNext1);
        buttonNext1.setText(R.string.n_ste);
        buttonNext1.setOnClickListener(this);

        buttonTilbage1 = rod.findViewById(R.id.buttonTilbage1);
        buttonTilbage1.setText(R.string.tilbage);
        buttonTilbage1.setOnClickListener(this);

        hvorMangeMlVand = rod.findViewById(R.id.textViewVand);
        hvorMangeMlVand.setText(R.string.waterCoffeeRatioTV);

        scrollChoice = rod.findViewById(R.id.scroll_choice_vand);
        loadDeForskelligeMængder();

        mlVand = 45;

        scrollChoice.addItems(mlVandScroll,15); //default index, så den er på "60"
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                mlVand = Integer.parseInt(name.substring(0, name.length()-3));
                System.out.println("Ml vand: "+ mlVand);
            }
        });

        return rod;
    }
    private void loadDeForskelligeMængder(){

        for(int i = 30; i <= 60; i++) {
            mlVandScroll.add(i + " ml");
        }

    }

    @Override
    public void onClick(View v) {
        if (v == buttonNext1){
            progressBarStatus +=16;
            progressBar.setProgress(progressBarStatus);
            RecipeFactoryController.getInstance().setWaterAmount(mlVand);
        getFragmentManager().beginTransaction()
                //.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                .replace(R.id.broegFragmentetIActivity, new BrewingTemperature())
                .addToBackStack(null)
                .commit();}
        else if (v == buttonTilbage1){
            getFragmentManager().beginTransaction()
                  //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
                    .replace(R.id.broegFragmentetIActivity, new GrindSize())
                    .addToBackStack(null)
                    .commit();
        }
    }
}