package grp02.brg_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.webianks.library.scroll_choice.ScrollChoice;

import java.util.ArrayList;
import java.util.List;

public class VandFordelingsFragment extends Fragment implements View.OnClickListener {


    List<String> antalSekunder = new ArrayList<>();
    TextView hvorMangeSekunder;
    ScrollChoice scrollChoice;
    Button buttonNext2, buttonTilbage2;

    @Override
    public View onCreateView(LayoutInflater i,ViewGroup container,Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_vand_fordelings_tid,container, false);

        buttonNext2 = rod.findViewById(R.id.buttonNext2);
        buttonNext2.setText("NÆSTE ->");
        buttonNext2.setOnClickListener(this);

        buttonTilbage2 = rod.findViewById(R.id.buttonTilbage2);
        buttonTilbage2.setText("<- TILBAGE");
        buttonTilbage2.setOnClickListener(this);

        hvorMangeSekunder = rod.findViewById(R.id.textViewSekunder);
        hvorMangeSekunder.setText("HVOR MEGET VAND PR. GRAM KAFFE VIL DU BRUGE?");

        scrollChoice = rod.findViewById(R.id.scroll_choice_sekunder);
        loadDeForskelligeMængder();

        scrollChoice.addItems(antalSekunder,15); //default index, så den er på "60"
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                //Implementere at gemme værdien i et objekt for den kaffe man er i gang med at lave.
            }
        });


        return rod;
    }
    private void loadDeForskelligeMængder(){
        antalSekunder.add("30 sek");
        antalSekunder.add("31 sek");
        antalSekunder.add("32 sek");
        antalSekunder.add("33 sek");
        antalSekunder.add("34 sek");
        antalSekunder.add("35 sek");
        antalSekunder.add("36 sek");
        antalSekunder.add("37 sek");
        antalSekunder.add("38 sek");
        antalSekunder.add("39 sek");
        antalSekunder.add("40 sek");
        antalSekunder.add("41 sek");
        antalSekunder.add("42 sek");
        antalSekunder.add("43 sek");
        antalSekunder.add("44 sek");
        antalSekunder.add("45 sek");
        antalSekunder.add("46 sek");
        antalSekunder.add("47 sek");
        antalSekunder.add("48 sek");
        antalSekunder.add("49 sek");
        antalSekunder.add("50 sek");
        antalSekunder.add("51 sek");
        antalSekunder.add("52 sek");
        antalSekunder.add("53 sek");
        antalSekunder.add("54 sek");
        antalSekunder.add("55 sek");
        antalSekunder.add("56 sek");
        antalSekunder.add("57 sek");
        antalSekunder.add("58 sek");
        antalSekunder.add("59 sek");
        antalSekunder.add("60 sek");

    }


    @Override
    public void onClick(View v) {
        if (v == buttonNext2){
            getFragmentManager().beginTransaction()
                    //.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.broegFragmentetIActivity, new HvorMegetVandFragment())
                    .addToBackStack(null)
                    .commit();}
        else if (v == buttonTilbage2){
            getFragmentManager().beginTransaction()
                    //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
                    .replace(R.id.broegFragmentetIActivity, new HvorMegetVandFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }
}