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

import grp02.brg_app.R;

public class HvorMegetVandTilBloomFragment extends Fragment implements View.OnClickListener {


    List<String> mlVandTilBloom = new ArrayList<>();
    String navn;
    double gramKaffeObjekt;
    int mlVandObjekt, antalSekunderObjekt, mlVandTilBloomObjekt;

    TextView hvorMangeMlVand;
    ScrollChoice scrollChoice;
    Button buttonNext3, buttonTilbage3;
    ProgressBar progressBar;
    int progressBarStatus = 60;
    Bundle bundle = new Bundle();


    @Override
    public View onCreateView(LayoutInflater i,ViewGroup container,Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_hvor_meget_vand_til_bloom,container, false);

        Bundle bundleArg = getArguments();
        if (bundleArg != null){
            navn = bundleArg.getString("navnpåBrygObjekt");
            gramKaffeObjekt = bundleArg.getDouble("gramKaffeObjekt");
            mlVandObjekt = bundleArg.getInt("mlVandObjekt");
            antalSekunderObjekt = bundleArg.getInt("antalSekunderObjekt");
        }
        bundle.putString("navnpåBrygObjekt", navn);
        bundle.putDouble("gramKaffeObjekt", gramKaffeObjekt);
        bundle.putInt("mlVandObjekt", mlVandObjekt);
        bundle.putInt("antalSekunderObjekt", antalSekunderObjekt);

        System.out.println("Fået fra bundle, navn" + navn);
        System.out.println("Fået fra bundle, kaffe: " + gramKaffeObjekt);
        System.out.println("Fået fra bundle, ml vand: " + mlVandObjekt);
        System.out.println("Fået fra bundle, antal sekunder: " + antalSekunderObjekt);

        progressBar = rod.findViewById(R.id.progressBar3);
        progressBar.setProgress(progressBarStatus);

        buttonNext3 = rod.findViewById(R.id.buttonNext3);
        buttonNext3.setText("NÆSTE ->");
        buttonNext3.setOnClickListener(this);

        buttonTilbage3 = rod.findViewById(R.id.buttonTilbage3);
        buttonTilbage3.setText("<- TILBAGE");
        buttonTilbage3.setOnClickListener(this);

        hvorMangeMlVand = rod.findViewById(R.id.textViewBloomTid);
        hvorMangeMlVand.setText("HVOR MEGET VAND SKAL DU BRUGE TIL BLOOM?");

        scrollChoice = rod.findViewById(R.id.scroll_choice_BloomTid);
        loadDeForskelligeMængder();

        mlVandTilBloomObjekt = 45;
        bundle.putInt("mlVandTilBloomObjekt", mlVandTilBloomObjekt);

        scrollChoice.addItems(mlVandTilBloom,15); //default index, så den er på "60"
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                mlVandTilBloomObjekt = Integer.parseInt(name.substring(0, name.length()-3));
                bundle.putInt("mlVandTilBloomObjekt", mlVandTilBloomObjekt);
                System.out.println("Ml vand til Bloom: "+mlVandTilBloomObjekt);
            }
        });


        return rod;
    }
    private void loadDeForskelligeMængder(){
        mlVandTilBloom.add("30 ml");
        mlVandTilBloom.add("31 ml");
        mlVandTilBloom.add("32 ml");
        mlVandTilBloom.add("33 ml");
        mlVandTilBloom.add("34 ml");
        mlVandTilBloom.add("35 ml");
        mlVandTilBloom.add("36 ml");
        mlVandTilBloom.add("37 ml");
        mlVandTilBloom.add("38 ml");
        mlVandTilBloom.add("39 ml");
        mlVandTilBloom.add("40 ml");
        mlVandTilBloom.add("41 ml");
        mlVandTilBloom.add("42 ml");
        mlVandTilBloom.add("43 ml");
        mlVandTilBloom.add("44 ml");
        mlVandTilBloom.add("45 ml");
        mlVandTilBloom.add("46 ml");
        mlVandTilBloom.add("47 ml");
        mlVandTilBloom.add("48 ml");
        mlVandTilBloom.add("49 ml");
        mlVandTilBloom.add("50 ml");
        mlVandTilBloom.add("51 ml");
        mlVandTilBloom.add("52 ml");
        mlVandTilBloom.add("53 ml");
        mlVandTilBloom.add("54 ml");
        mlVandTilBloom.add("55 ml");
        mlVandTilBloom.add("56 ml");
        mlVandTilBloom.add("57 ml");
        mlVandTilBloom.add("58 ml");
        mlVandTilBloom.add("59 ml");
        mlVandTilBloom.add("60 ml");

    }


    @Override
    public void onClick(View v) {
        if (v == buttonNext3){
            progressBarStatus += 20;
            progressBar.setProgress(progressBarStatus);

            //Sende dataen til næste fragment, så man kan se hvad værdi man har valgt
            BloomvandDistribueringFragment bloomvandDistribueringFragment = new BloomvandDistribueringFragment();
            bloomvandDistribueringFragment.setArguments(bundle);

            getFragmentManager().beginTransaction()
                    //.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.broegFragmentetIActivity, bloomvandDistribueringFragment)
                    .addToBackStack(null)
                    .commit();}
        else if (v == buttonTilbage3){
            VandFordelingsFragment vandFordelingsFragment = new VandFordelingsFragment();
            vandFordelingsFragment.setArguments(bundle);
            getFragmentManager().beginTransaction()
                    //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
                    .replace(R.id.broegFragmentetIActivity, vandFordelingsFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}

