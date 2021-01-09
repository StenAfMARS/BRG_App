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

public class VandFordelingsFragment extends Fragment implements View.OnClickListener {


    List<String> antalSekunder = new ArrayList<>();
    String navn;
    double gramKaffeObjekt;
    int mlVandObjekt, antalSekunderObjekt;
    TextView hvorMangeSekunder;
    ScrollChoice scrollChoice;
    Button buttonNext2, buttonTilbage2;
    ProgressBar progressBar;
    int progressBarStatus = 40;

    @Override
    public View onCreateView(LayoutInflater i,ViewGroup container,Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_vand_fordelings_tid,container, false);

        progressBar = rod.findViewById(R.id.progressBar2);
        progressBar.setProgress(progressBarStatus);

        buttonNext2 = rod.findViewById(R.id.buttonNext2);
        buttonNext2.setText("NÆSTE");
        buttonNext2.setOnClickListener(this);

        buttonTilbage2 = rod.findViewById(R.id.buttonTilbage2);
        buttonTilbage2.setText("TILBAGE");
        buttonTilbage2.setOnClickListener(this);

        hvorMangeSekunder = rod.findViewById(R.id.textViewSekunder);
        hvorMangeSekunder.setText("HVOR HURTIGT SKAL VANDET FORDELES?");

        scrollChoice = rod.findViewById(R.id.scroll_choice_sekunder);
        loadDeForskelligeMængder();

        antalSekunderObjekt = 45;

        scrollChoice.addItems(antalSekunder,15); //default index, så den er på "60"
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                antalSekunderObjekt = Integer.parseInt(name.substring(0, name.length()-4));
                System.out.println("Antal Sekunder: "+antalSekunderObjekt);
            }
        });

        RecipeFactory.getInstance().setBloomTime(antalSekunderObjekt);

        return rod;
    }
    private void loadDeForskelligeMængder(){

        for(int i = 30; i <= 60; i++) {
            antalSekunder.add(i + " sek");
        }

    }


    @Override
    public void onClick(View v) {
        if (v == buttonNext2){
            progressBarStatus += 20;
            progressBar.setProgress(progressBarStatus);

            //Sende dataen til næste fragment, så man kan se hvad værdi man har valgt
            HvorMegetVandTilBloomFragment hvorMegetVandTilBloomFragment = new HvorMegetVandTilBloomFragment();

            getFragmentManager().beginTransaction()
                    //.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.broegFragmentetIActivity, hvorMegetVandTilBloomFragment)
                    .addToBackStack(null)
                    .commit();}
        else if (v == buttonTilbage2){
            HvorMegetVandFragment hvorMegetVandFragment = new HvorMegetVandFragment();
            getFragmentManager().beginTransaction()
                    //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
                    .replace(R.id.broegFragmentetIActivity, hvorMegetVandFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}