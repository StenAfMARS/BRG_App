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
import grp02.brg_app.Model.BrygObjekt;
import grp02.brg_app.R;

public class BroegFragmentet extends Fragment implements View.OnClickListener {


    List<String> gramKaffe = new ArrayList<>();
    double gramKaffeObjekt;
    TextView hvorMangeKramKaffe;
    ScrollChoice scrollChoice;
    Button buttonNext, buttonTilbage;
    ProgressBar progressBar;
    int progressBarStatus;

    @Override
    public View onCreateView(LayoutInflater i,ViewGroup container,Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_hvor_meget_kaffe,container, false);


        progressBar = rod.findViewById(R.id.progressBar);

        buttonNext = rod.findViewById(R.id.buttonNext);
        buttonNext.setText("NÆSTE");
        buttonNext.setOnClickListener(this);

        buttonTilbage = rod.findViewById(R.id.buttonTilbage);
        buttonTilbage.setText("TILBAGE");
        buttonTilbage.setOnClickListener(this);

        hvorMangeKramKaffe = rod.findViewById(R.id.textView);
        hvorMangeKramKaffe.setText("HVOR MANGE GRAM KAFFE VIL DU HAVE?");

        scrollChoice = rod.findViewById(R.id.scroll_choice);
        loadDeForskelligeMængder();



        scrollChoice.addItems(gramKaffe,50); //default index, så den er på "60"
        gramKaffeObjekt = 60;
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                gramKaffeObjekt = Double.parseDouble(name.substring(0, name.length()-2));
                System.out.println("Gram Kaffe: "+gramKaffeObjekt);
            }
        });

        RecipeFactory.getInstance().setCoffeeToWater((float) gramKaffeObjekt);

        return rod;
    }

    private void loadDeForskelligeMængder(){

        for(int i = 50; i <= 75; i++) {
            for(int j = 0; j < 10; j++) {
                gramKaffe.add(i + "." + j + " G");
            }
        }

    }


    @Override
    public void onClick(View v) {
        if (v == buttonNext){
            progressBarStatus +=20;
            progressBar.setProgress(progressBarStatus);
            HvorMegetVandFragment hvorMegetVandFragment = new HvorMegetVandFragment();
            getFragmentManager().beginTransaction()
                  //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.broegFragmentetIActivity, hvorMegetVandFragment)
                    .addToBackStack(null)
                    .commit();
        } else if (v == buttonTilbage){
            StartBroeg startBroeg = new StartBroeg();
            getFragmentManager().beginTransaction()
                    //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
                    .replace(R.id.broegFragmentetIActivity, startBroeg)
                    .addToBackStack(null)
                    .commit();
        }
    }
}