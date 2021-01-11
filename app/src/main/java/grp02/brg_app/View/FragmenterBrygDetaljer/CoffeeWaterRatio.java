package grp02.brg_app.View.FragmenterBrygDetaljer;

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

import grp02.brg_app.Control.RecipeFactory;
import grp02.brg_app.R;

public class CoffeeWaterRatio extends Fragment implements View.OnClickListener {


    List<String> mlVandScroll = new ArrayList<>();
    int mlVand;
    TextView hvorMangeMlVand;
    ScrollChoice scrollChoice;
    Button buttonNext1, buttonTilbage1;
    ProgressBar progressBar;
    int progressBarStatus = 20;
    Bundle bundle = new Bundle();


    @Override
    public View onCreateView(LayoutInflater i,ViewGroup container,Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_hvor_meget_vand,container, false);

        progressBar = rod.findViewById(R.id.progressBar1);
        progressBar.setProgress(progressBarStatus);
        buttonNext1 = rod.findViewById(R.id.buttonNext1);
        buttonNext1.setText("NÆSTE");
        buttonNext1.setOnClickListener(this);

        buttonTilbage1 = rod.findViewById(R.id.buttonTilbage1);
        buttonTilbage1.setText("TILBAGE");
        buttonTilbage1.setOnClickListener(this);

        hvorMangeMlVand = rod.findViewById(R.id.textViewVand);
        hvorMangeMlVand.setText("HVOR MEGET VAND PR. GRAM KAFFE VIL DU BRUGE?");

        scrollChoice = rod.findViewById(R.id.scroll_choice_vand);
        loadDeForskelligeMængder();

        mlVand = 45;
        bundle.putInt("mlVandObjekt", mlVand);

        scrollChoice.addItems(mlVandScroll,15); //default index, så den er på "60"
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                mlVand = Integer.parseInt(name.substring(0, name.length()-3));
               bundle.putInt("mlVandObjekt", mlVand);
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
            progressBarStatus +=20;
            progressBar.setProgress(progressBarStatus);
            RecipeFactory.getInstance().setWaterAmount(mlVand);
            BrewingTemperature brewingTemperature = new BrewingTemperature();
        getFragmentManager().beginTransaction()
                //.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                .replace(R.id.broegFragmentetIActivity, brewingTemperature)
                .addToBackStack(null)
                .commit();}
        else if (v == buttonTilbage1){
            GrindSize grindSize = new GrindSize();
            getFragmentManager().beginTransaction()
                  //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
                    .replace(R.id.broegFragmentetIActivity, grindSize)
                    .addToBackStack(null)
                    .commit();
        }
    }
}