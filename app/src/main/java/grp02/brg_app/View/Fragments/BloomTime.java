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

public class BloomTime extends Fragment implements View.OnClickListener {

    private List<String> bloomDistTimeScroll = new ArrayList<>();
    private int bloomDistTime;
    private TextView textViewDistribuering;
    private ScrollChoice scrollChoice;
    private Button buttonNext4, buttonTilbage4;
    private ProgressBar progressBar;
    private int progressBarStatus = 80;

    @Override
    public View onCreateView(LayoutInflater i,ViewGroup container,Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_bloomvand_distribuering,container, false);

        progressBar = rod.findViewById(R.id.progressBar4);
        progressBar.setProgress(progressBarStatus);

        buttonNext4 = rod.findViewById(R.id.buttonNext4);
        buttonNext4.setText("NÆSTE");
        buttonNext4.setOnClickListener(this);

        buttonTilbage4 = rod.findViewById(R.id.buttonTilbage4);
        buttonTilbage4.setText("TILBAGE");
        buttonTilbage4.setOnClickListener(this);

        textViewDistribuering = rod.findViewById(R.id.textViewDistribuering);
        textViewDistribuering.setText("HVOR HURTIGT SKAL BLOOMVANDET DISTRIBUERES?");

        scrollChoice = rod.findViewById(R.id.scroll_choice_Distribuering);
        loadDeForskelligeMængder();

        bloomDistTime = 45;

        scrollChoice.addItems(bloomDistTimeScroll,15); //default index, så den er på "60"
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                //Implementere at gemme værdien i et objekt for den kaffe man er i gang med at lave.
                bloomDistTime = Integer.parseInt(name.substring(0, name.length()-4));
                System.out.println("Antal sekunder til bloomvandsdistribuering: "+ bloomDistTime);
            }
        });


        return rod;
    }
    private void loadDeForskelligeMængder(){

        for(int i = 30; i <= 60; i++) {
            bloomDistTimeScroll.add(i + " sek");
        }

    }


    @Override
    public void onClick(View v) {
        if (v == buttonNext4){
            progressBarStatus += 20;
            progressBar.setProgress(progressBarStatus);
            RecipeFactoryController.getInstance().setBloomTime(bloomDistTime);
            getFragmentManager().beginTransaction()
                    //.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.broegFragmentetIActivity, new FinalBroeg())
                    .addToBackStack(null)
                    .commit();

        }
        else if (v == buttonTilbage4){
            getFragmentManager().beginTransaction()
                    //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
                    .replace(R.id.broegFragmentetIActivity, new BloomWater())
                    .addToBackStack(null)
                    .commit();
        }
    }
}