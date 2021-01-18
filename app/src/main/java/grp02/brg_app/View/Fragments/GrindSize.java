package grp02.brg_app.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.webianks.library.scroll_choice.ScrollChoice;

import java.util.ArrayList;
import java.util.List;

import grp02.brg_app.Control.RecipeFactoryController;
import grp02.brg_app.R;

public class GrindSize extends Fragment implements View.OnClickListener {


    private List<String> grindSizeScroll = new ArrayList<>();
    private String grindSize;
    private TextView hvorMangeKramKaffe;
    private ScrollChoice scrollChoice;
    private Button buttonNext, buttonTilbage;
    private ProgressBar progressBar;
    private int progressBarStatus = 16;

    @Override
    public View onCreateView(LayoutInflater i,ViewGroup container,Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_grind_size,container, false);

        progressBar = rod.findViewById(R.id.progressBarGrind);
        progressBar.setProgress(progressBarStatus);

        buttonNext = rod.findViewById(R.id.buttonNextGrind);
        buttonNext.setText(R.string.n_ste);
        buttonNext.setOnClickListener(this);

        buttonTilbage = rod.findViewById(R.id.buttonTilbageGrind);
        buttonTilbage.setText(R.string.tilbage);
        buttonTilbage.setOnClickListener(this);

        hvorMangeKramKaffe = rod.findViewById(R.id.textViewGrind);
        hvorMangeKramKaffe.setText(R.string.GrindSizeTV);

        scrollChoice = rod.findViewById(R.id.scroll_choice_grind);
        fillGrindSizeScroll();
        grindSize = "medium";
        scrollChoice.addItems(grindSizeScroll, 1);
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                grindSize = name;
            }
        });

        return rod;
    }
    private void fillGrindSizeScroll(){
            grindSizeScroll.add("Fin");
            grindSizeScroll.add("Medium");
            grindSizeScroll.add("Rug");
    }

    @Override
    public void onClick(View v) {
        if (v == buttonNext){
            progressBarStatus +=16;
            progressBar.setProgress(progressBarStatus);
            RecipeFactoryController.getInstance().setGrindSize(grindSize);
            getFragmentManager().beginTransaction()
                  //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.broegFragmentetIActivity, new WaterCoffeeRatio())
                    .addToBackStack(null)
                    .commit();
        } else if (v == buttonTilbage){
            getFragmentManager().beginTransaction()
                    //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
                    .replace(R.id.broegFragmentetIActivity, new GroundCoffee())
                    .addToBackStack(null)
                    .commit();
        }
    }
}