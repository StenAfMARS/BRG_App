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

import grp02.brg_app.Control.LogicController;
import grp02.brg_app.Control.RecipeFactoryController;
import grp02.brg_app.R;

public class GroundCoffee extends Fragment implements View.OnClickListener {


    private List<String> gramKaffeScroll = new ArrayList<>();
    private List<String> milliGramKaffeScroll = new ArrayList<>();
    private String gramKaffe, milliGramKaffe;
    private TextView hvorMangeKramKaffe;
    private ScrollChoice scrollChoice, scrollChoice2;
    private Button buttonNext, buttonTilbage;
    private ProgressBar progressBar;
    private int progressBarStatus = 0;

    @Override
    public View onCreateView(LayoutInflater i,ViewGroup container,Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_hvor_meget_kaffe,container, false);

        progressBar = rod.findViewById(R.id.progressBar);
        progressBar.setProgress(progressBarStatus);

        buttonNext = rod.findViewById(R.id.buttonNext);
        buttonNext.setText(R.string.n_ste);
        buttonNext.setOnClickListener(this);

        buttonTilbage = rod.findViewById(R.id.buttonTilbage);
        buttonTilbage.setText(R.string.tilbage);
        buttonTilbage.setOnClickListener(this);

        hvorMangeKramKaffe = rod.findViewById(R.id.textView);
        hvorMangeKramKaffe.setText(R.string.groundCoffeeTV);

        scrollChoice = rod.findViewById(R.id.scroll_choice);
        scrollChoice2 = rod.findViewById(R.id.scroll_choice2);
        gramKaffeScroll = LogicController.getInstance().loadMængderIGram();
        milliGramKaffeScroll = LogicController.getInstance().loadMængderIMilliGram();

        scrollChoice.addItems(gramKaffeScroll, 10); //default index, så den er på "60"
        gramKaffe = "60";
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                gramKaffe = name;
            }
        });

        scrollChoice2.addItems(milliGramKaffeScroll, 0);
        milliGramKaffe = "0";
        scrollChoice2.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                milliGramKaffe = name;
            }
        });

        return rod;
    }

    @Override
    public void onClick(View v) {
        System.out.println("Gram Kaffe: " + gramKaffe + "," + milliGramKaffe + " g");
        if (v == buttonNext){
            progressBarStatus +=16;
            progressBar.setProgress(progressBarStatus);
            RecipeFactoryController.getInstance().setGroundCoffee(LogicController.getInstance().convertStringsToFloats(gramKaffe, milliGramKaffe));
            getFragmentManager().beginTransaction()
                  //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.broegFragmentetIActivity, new GrindSize())
                    .addToBackStack(null)
                    .commit();
        } else if (v == buttonTilbage){
            getFragmentManager().beginTransaction()
                    //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
                    .replace(R.id.broegFragmentetIActivity, new NameStart())
                    .addToBackStack(null)
                    .commit();
        }
    }
}