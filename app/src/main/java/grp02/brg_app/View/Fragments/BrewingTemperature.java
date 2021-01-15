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

public class BrewingTemperature extends Fragment implements View.OnClickListener {

    private List<String> tempScroll = new ArrayList<>();
    private int temp;
    private TextView temperatur;
    private ScrollChoice scrollChoice;
    private Button buttonNext2, buttonTilbage2;
    private ProgressBar progressBar;
    private int progressBarStatus = 48;

    @Override
    public View onCreateView(LayoutInflater i,ViewGroup container,Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_brewing_temperature,container, false);

        progressBar = rod.findViewById(R.id.progressBar2);
        progressBar.setProgress(progressBarStatus);

        buttonNext2 = rod.findViewById(R.id.buttonNext2);
        buttonNext2.setText("NÆSTE");
        buttonNext2.setOnClickListener(this);

        buttonTilbage2 = rod.findViewById(R.id.buttonTilbage2);
        buttonTilbage2.setText("TILBAGE");
        buttonTilbage2.setOnClickListener(this);

        temperatur = rod.findViewById(R.id.textViewSekunder);
        temperatur.setText("HVAD SKAL TEMPERATUREN VÆRE?");

        scrollChoice = rod.findViewById(R.id.scroll_choice_sekunder);
        loadDeForskelligeMængder();

        temp = 90;

        scrollChoice.addItems(tempScroll,10); //default index, så den er på "60"
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                temp = Integer.parseInt(name.substring(0, name.length()-9));
                System.out.println("Temperatur: "+ temp);
            }
        });

        return rod;
    }
    private void loadDeForskelligeMængder(){

        for(int i = 80; i <= 99; i++) {
            tempScroll.add(i + " grader C");
        }

    }

    @Override
    public void onClick(View v) {
        if (v == buttonNext2){
            progressBarStatus += 16;
            progressBar.setProgress(progressBarStatus);
            RecipeFactoryController.getInstance().setBrewingTemperature(temp);
            getFragmentManager().beginTransaction()
                    //.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.broegFragmentetIActivity, new BloomWater())
                    .addToBackStack(null)
                    .commit();}
        else if (v == buttonTilbage2){
            getFragmentManager().beginTransaction()
                    //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
                    .replace(R.id.broegFragmentetIActivity, new WaterCoffeeRatio())
                    .addToBackStack(null)
                    .commit();
        }
    }
}