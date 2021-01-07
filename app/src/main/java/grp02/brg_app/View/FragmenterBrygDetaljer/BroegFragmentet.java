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
import java.util.Collections;
import java.util.List;

import grp02.brg_app.Control.LogicController;
import grp02.brg_app.Control.RecipeFactory;
import grp02.brg_app.Model.BrygObjekt;
import grp02.brg_app.R;

public class BroegFragmentet extends Fragment implements View.OnClickListener {


    List<String> gramKaffe = new ArrayList<>();
    List<String> milliGramKaffe = new ArrayList<>();
    String gramKaffeObjekt;
    String milliGramKaffeObjekt;
    TextView hvorMangeKramKaffe;
    ScrollChoice scrollChoice, scrollChoice2;
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
        scrollChoice2 = rod.findViewById(R.id.scroll_choice2);
        gramKaffe = LogicController.getInstance().loadMængderIGram();
        milliGramKaffe = LogicController.getInstance().loadMængderIMilliGram();


        scrollChoice.addItems(gramKaffe, 10); //default index, så den er på "60"
        gramKaffeObjekt = "60";
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                gramKaffeObjekt = name;
            }
        });

        scrollChoice2.addItems(milliGramKaffe, 0);
        milliGramKaffeObjekt = "0";
        scrollChoice2.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                milliGramKaffeObjekt = name;
            }
        });


        RecipeFactory.getInstance().setCoffeeToWater(LogicController.getInstance().convertStringsToFloats(gramKaffeObjekt, milliGramKaffeObjekt));
        return rod;
    }

    @Override
    public void onClick(View v) {
        System.out.println("Gram Kaffe: " + gramKaffeObjekt + "," + milliGramKaffeObjekt + " g");
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


            System.out.println("This is RecipeFactory Value: " + RecipeFactory.getInstance().getCoffeeToWater());
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