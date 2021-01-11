package grp02.brg_app.View.FragmenterBrygDetaljer;

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

import grp02.brg_app.Control.LogicController;
import grp02.brg_app.Control.RecipeFactory;
import grp02.brg_app.R;

public class GrindSize extends Fragment implements View.OnClickListener {


    List<String> grindSizeScroll = new ArrayList<>();
    String grindSize;
    TextView hvorMangeKramKaffe;
    ScrollChoice scrollChoice;
    Button buttonNext, buttonTilbage;
    ProgressBar progressBar;
    int progressBarStatus = 0;

    @Override
    public View onCreateView(LayoutInflater i,ViewGroup container,Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_grind_size,container, false);

        progressBar = rod.findViewById(R.id.progressBarGrind);

        buttonNext = rod.findViewById(R.id.buttonNextGrind);
        buttonNext.setText("NÆSTE");
        buttonNext.setOnClickListener(this);

        buttonTilbage = rod.findViewById(R.id.buttonTilbageGrind);
        buttonTilbage.setText("TILBAGE");
        buttonTilbage.setOnClickListener(this);

        hvorMangeKramKaffe = rod.findViewById(R.id.textViewGrind);
        hvorMangeKramKaffe.setText("HVOR MANGE GRAM KAFFE VIL DU HAVE?");

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
            grindSizeScroll.add("fine");
            grindSizeScroll.add("medium");
            grindSizeScroll.add("coarse");
    }

    @Override
    public void onClick(View v) {
        System.out.println("Grind Size " + grindSize );
        if (v == buttonNext){
            progressBarStatus +=20;
            progressBar.setProgress(progressBarStatus);
            RecipeFactory.getInstance().setGrindSize(grindSize);
            System.out.println("This is RecipeFactory Value: " + RecipeFactory.getInstance().getCoffeeToWater());
            WaterCoffeeRatio waterCoffeeRatio = new WaterCoffeeRatio();
            getFragmentManager().beginTransaction()
                  //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.broegFragmentetIActivity, waterCoffeeRatio)
                    .addToBackStack(null)
                    .commit();
        } else if (v == buttonTilbage){
            GroundCoffee groundCoffee = new GroundCoffee();
            getFragmentManager().beginTransaction()
                    //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
                    .replace(R.id.broegFragmentetIActivity, groundCoffee)
                    .addToBackStack(null)
                    .commit();
        }
    }
}