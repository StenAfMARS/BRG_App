package grp02.brg_app.FragmenterBrygDetaljer;

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

import grp02.brg_app.R;

public class HvorMegetVandFragment extends Fragment implements View.OnClickListener {


    List<String> gramVand = new ArrayList<>();
    TextView hvorMangeMlVand;
    ScrollChoice scrollChoice;
    Button buttonNext1, buttonTilbage1;
    ProgressBar progressBar;
    int progressBarStatus = 20;

    @Override
    public View onCreateView(LayoutInflater i,ViewGroup container,Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_hvor_meget_vand,container, false);

        progressBar = rod.findViewById(R.id.progressBar1);
        progressBar.setProgress(progressBarStatus);
        buttonNext1 = rod.findViewById(R.id.buttonNext1);
        buttonNext1.setText("NÆSTE ->");
        buttonNext1.setOnClickListener(this);

        buttonTilbage1 = rod.findViewById(R.id.buttonTilbage1);
        buttonTilbage1.setText("<- TILBAGE");
        buttonTilbage1.setOnClickListener(this);

        hvorMangeMlVand = rod.findViewById(R.id.textViewVand);
        hvorMangeMlVand.setText("HVOR MEGET VAND PR. GRAM KAFFE VIL DU BRUGE?");

        scrollChoice = rod.findViewById(R.id.scroll_choice_vand);
        loadDeForskelligeMængder();

        scrollChoice.addItems(gramVand,15); //default index, så den er på "60"
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                //Implementere at gemme værdien i et objekt for den kaffe man er i gang med at lave.
            }
        });


        return rod;
    }
    private void loadDeForskelligeMængder(){
        gramVand.add("30 ml");
        gramVand.add("31 ml");
        gramVand.add("32 ml");
        gramVand.add("33 ml");
        gramVand.add("34 ml");
        gramVand.add("35 ml");
        gramVand.add("36 ml");
        gramVand.add("37 ml");
        gramVand.add("38 ml");
        gramVand.add("39 ml");
        gramVand.add("40 ml");
        gramVand.add("41 ml");
        gramVand.add("42 ml");
        gramVand.add("43 ml");
        gramVand.add("44 ml");
        gramVand.add("45 ml");
        gramVand.add("46 ml");
        gramVand.add("47 ml");
        gramVand.add("48 ml");
        gramVand.add("49 ml");
        gramVand.add("50 ml");
        gramVand.add("51 ml");
        gramVand.add("52 ml");
        gramVand.add("53 ml");
        gramVand.add("54 ml");
        gramVand.add("55 ml");
        gramVand.add("56 ml");
        gramVand.add("57 ml");
        gramVand.add("58 ml");
        gramVand.add("59 ml");
        gramVand.add("60 ml");

    }


    @Override
    public void onClick(View v) {
        if (v == buttonNext1){
            progressBarStatus +=20;
            progressBar.setProgress(progressBarStatus);
        getFragmentManager().beginTransaction()
                //.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                .replace(R.id.broegFragmentetIActivity, new VandFordelingsFragment())
                .addToBackStack(null)
                .commit();}
        else if (v == buttonTilbage1){
            getFragmentManager().beginTransaction()
                  //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
                    .replace(R.id.broegFragmentetIActivity, new BroegFragmentet())
                    .addToBackStack(null)
                    .commit();
        }
    }
}