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

public class BloomvandDistribueringFragment extends Fragment implements View.OnClickListener {

    List<String> antalSekunder1 = new ArrayList<>();
    TextView textViewDistribuering;
    ScrollChoice scrollChoice;
    Button buttonNext3, buttonTilbage3;

    @Override
    public View onCreateView(LayoutInflater i,ViewGroup container,Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_bloomvand_distribuering,container, false);

        buttonNext3 = rod.findViewById(R.id.buttonNext3);
        buttonNext3.setText("BRØG");
        buttonNext3.setOnClickListener(this);

        buttonTilbage3 = rod.findViewById(R.id.buttonTilbage3);
        buttonTilbage3.setText("<- TILBAGE");
        buttonTilbage3.setOnClickListener(this);

        textViewDistribuering = rod.findViewById(R.id.textViewDistribuering);
        textViewDistribuering.setText("HVOR HURTIGT SKAL BLOOMVANDET DISTRIBRUERES?");

        scrollChoice = rod.findViewById(R.id.scroll_choice_Distribuering);
        loadDeForskelligeMængder();

        scrollChoice.addItems(antalSekunder1,15); //default index, så den er på "60"
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                //Implementere at gemme værdien i et objekt for den kaffe man er i gang med at lave.
            }
        });


        return rod;
    }
    private void loadDeForskelligeMængder(){
        antalSekunder1.add("30 sek");
        antalSekunder1.add("31 sek");
        antalSekunder1.add("32 sek");
        antalSekunder1.add("33 sek");
        antalSekunder1.add("34 sek");
        antalSekunder1.add("35 sek");
        antalSekunder1.add("36 sek");
        antalSekunder1.add("37 sek");
        antalSekunder1.add("38 sek");
        antalSekunder1.add("39 sek");
        antalSekunder1.add("40 sek");
        antalSekunder1.add("41 sek");
        antalSekunder1.add("42 sek");
        antalSekunder1.add("43 sek");
        antalSekunder1.add("44 sek");
        antalSekunder1.add("45 sek");
        antalSekunder1.add("46 sek");
        antalSekunder1.add("47 sek");
        antalSekunder1.add("48 sek");
        antalSekunder1.add("49 sek");
        antalSekunder1.add("50 sek");
        antalSekunder1.add("51 sek");
        antalSekunder1.add("52 sek");
        antalSekunder1.add("53 sek");
        antalSekunder1.add("54 sek");
        antalSekunder1.add("55 sek");
        antalSekunder1.add("56 sek");
        antalSekunder1.add("57 sek");
        antalSekunder1.add("58 sek");
        antalSekunder1.add("59 sek");
        antalSekunder1.add("60 sek");

    }


    @Override
    public void onClick(View v) {
        if (v == buttonNext3){
            getFragmentManager().beginTransaction()
                    //.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.broegFragmentetIActivity, new BroegFragmentet())
                    .addToBackStack(null)
                    .commit();}
        else if (v == buttonTilbage3){
            getFragmentManager().beginTransaction()
                    //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
                    .replace(R.id.broegFragmentetIActivity, new HvorMegetVandTilBloomFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }
}