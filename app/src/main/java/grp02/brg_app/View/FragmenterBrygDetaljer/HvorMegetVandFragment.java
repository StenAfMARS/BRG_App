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

public class HvorMegetVandFragment extends Fragment implements View.OnClickListener {


    List<String> mlVand = new ArrayList<>();
   // For at vise hvad man har tastet ind på tidligere fragment:
    String navn;
    double gramKaffeObjekt;
    int mlVandObjekt;
    TextView hvorMangeMlVand;
    ScrollChoice scrollChoice;
    Button buttonNext1, buttonTilbage1;
    ProgressBar progressBar;
    int progressBarStatus = 20;
    Bundle bundle = new Bundle();


    @Override
    public View onCreateView(LayoutInflater i,ViewGroup container,Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_hvor_meget_vand,container, false);
        //bliver ikke brugt endnu, men vil blive benyttet hvis man vil have vist hvad man valgt af værdi fra forrige fragment
        Bundle bundleArg = getArguments();
        if (bundleArg != null) {
            navn = bundleArg.getString("navnpåBrygObjekt");
            gramKaffeObjekt = bundleArg.getDouble("gramKaffeObjekt");
        }
        bundle.putString("navnpåBrygObjekt", navn);
        bundle.putDouble("gramKaffeObjekt", gramKaffeObjekt);


        System.out.println("Fået fra bundle, navn: " + navn);
        System.out.println("Fået fra bundle, gram kaffe: " + gramKaffeObjekt);


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

        mlVandObjekt = 45;
        bundle.putInt("mlVandObjekt", mlVandObjekt);

        scrollChoice.addItems(mlVand,15); //default index, så den er på "60"
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                mlVandObjekt = Integer.parseInt(name.substring(0, name.length()-3));
               bundle.putInt("mlVandObjekt", mlVandObjekt);
                System.out.println("Ml vand: "+mlVandObjekt);
            }
        });


        return rod;
    }
    private void loadDeForskelligeMængder(){
        mlVand.add("30 ml");
        mlVand.add("31 ml");
        mlVand.add("32 ml");
        mlVand.add("33 ml");
        mlVand.add("34 ml");
        mlVand.add("35 ml");
        mlVand.add("36 ml");
        mlVand.add("37 ml");
        mlVand.add("38 ml");
        mlVand.add("39 ml");
        mlVand.add("40 ml");
        mlVand.add("41 ml");
        mlVand.add("42 ml");
        mlVand.add("43 ml");
        mlVand.add("44 ml");
        mlVand.add("45 ml");
        mlVand.add("46 ml");
        mlVand.add("47 ml");
        mlVand.add("48 ml");
        mlVand.add("49 ml");
        mlVand.add("50 ml");
        mlVand.add("51 ml");
        mlVand.add("52 ml");
        mlVand.add("53 ml");
        mlVand.add("54 ml");
        mlVand.add("55 ml");
        mlVand.add("56 ml");
        mlVand.add("57 ml");
        mlVand.add("58 ml");
        mlVand.add("59 ml");
        mlVand.add("60 ml");

    }


    @Override
    public void onClick(View v) {
        if (v == buttonNext1){
            progressBarStatus +=20;
            progressBar.setProgress(progressBarStatus);
            VandFordelingsFragment vandFordelingsFragment = new VandFordelingsFragment();
            vandFordelingsFragment.setArguments(bundle);

        getFragmentManager().beginTransaction()
                //.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                .replace(R.id.broegFragmentetIActivity, vandFordelingsFragment)
                .addToBackStack(null)
                .commit();}
        else if (v == buttonTilbage1){
            BroegFragmentet broegFragmentet = new BroegFragmentet();
            broegFragmentet.setArguments(bundle);
            getFragmentManager().beginTransaction()
                  //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
                    .replace(R.id.broegFragmentetIActivity, broegFragmentet)
                    .addToBackStack(null)
                    .commit();
        }
    }
}