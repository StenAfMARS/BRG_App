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

import grp02.brg_app.Model.BrygObjekt;
import grp02.brg_app.R;

public class BloomvandDistribueringFragment extends Fragment implements View.OnClickListener {

    List<String> antalSekunderTilBloomVandDistribuering = new ArrayList<>();

    String navn;
    double gramKaffeObjekt;
    int mlVandObjekt, antalSekunderObjekt, antalSekunderTilBloomVandDistribueringObjekt, mlVandTilBloomObjekt;

    TextView textViewDistribuering;
    ScrollChoice scrollChoice;
    Button buttonNext4, buttonTilbage4;
    ProgressBar progressBar;
    int progressBarStatus = 80;
    Bundle bundle = new Bundle();

    @Override
    public View onCreateView(LayoutInflater i,ViewGroup container,Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_bloomvand_distribuering,container, false);

        //bliver ikke brugt endnu, men vil blive benyttet hvis man vil have vist hvad man valgt af værdi fra forrige fragment
        Bundle bundleArg = getArguments();
        if (bundleArg != null){
            navn = bundleArg.getString("navnpåBrygObjekt");
            gramKaffeObjekt = bundleArg.getDouble("gramKaffeObjekt");
            mlVandObjekt = bundleArg.getInt("mlVandObjekt");
            antalSekunderObjekt = bundleArg.getInt("antalSekunderObjekt");
            mlVandTilBloomObjekt = bundleArg.getInt("mlVandTilBloomObjekt");
        }
        bundle.putString("navnpåBrygObjekt", navn);
        bundle.putDouble("gramKaffeObjekt", gramKaffeObjekt);
        bundle.putInt("mlVandObjekt", mlVandObjekt);
        bundle.putInt("antalSekunderObjekt", antalSekunderObjekt);
        bundle.putInt("mlVandTilBloomObjekt", mlVandTilBloomObjekt);

        System.out.println("Fået fra bundle, navn: " + navn);
        System.out.println("Fået fra bundle, kaffe: " + gramKaffeObjekt);
        System.out.println("Fået fra bundle, ml vand: " + mlVandObjekt);
        System.out.println("Fået fra bundle, antal sekunder: " + antalSekunderObjekt);
        System.out.println("Fået fra bundle, ml vand til bloom: " + mlVandTilBloomObjekt);

        progressBar = rod.findViewById(R.id.progressBar4);
        progressBar.setProgress(progressBarStatus);

        buttonNext4 = rod.findViewById(R.id.buttonNext4);
        buttonNext4.setText("BRØG");
        buttonNext4.setOnClickListener(this);

        buttonTilbage4 = rod.findViewById(R.id.buttonTilbage4);
        buttonTilbage4.setText("<- TILBAGE");
        buttonTilbage4.setOnClickListener(this);

        textViewDistribuering = rod.findViewById(R.id.textViewDistribuering);
        textViewDistribuering.setText("HVOR HURTIGT SKAL BLOOMVANDET DISTRIBUERES?");

        scrollChoice = rod.findViewById(R.id.scroll_choice_Distribuering);
        loadDeForskelligeMængder();

        antalSekunderTilBloomVandDistribueringObjekt = 45;
        bundle.putInt("antalSekunderTilBloomVandDistribueringObjekt", antalSekunderTilBloomVandDistribueringObjekt);

        scrollChoice.addItems(antalSekunderTilBloomVandDistribuering,15); //default index, så den er på "60"
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                //Implementere at gemme værdien i et objekt for den kaffe man er i gang med at lave.
                antalSekunderTilBloomVandDistribueringObjekt = Integer.parseInt(name.substring(0, name.length()-4));
                bundle.putInt("antalSekunderTilBloomVandDistribueringObjekt", antalSekunderTilBloomVandDistribueringObjekt);
                System.out.println("Antal sekunder til bloomvandsdistribuering: "+antalSekunderTilBloomVandDistribueringObjekt);
            }
        });


        return rod;
    }
    private void loadDeForskelligeMængder(){
        antalSekunderTilBloomVandDistribuering.add("30 sek");
        antalSekunderTilBloomVandDistribuering.add("31 sek");
        antalSekunderTilBloomVandDistribuering.add("32 sek");
        antalSekunderTilBloomVandDistribuering.add("33 sek");
        antalSekunderTilBloomVandDistribuering.add("34 sek");
        antalSekunderTilBloomVandDistribuering.add("35 sek");
        antalSekunderTilBloomVandDistribuering.add("36 sek");
        antalSekunderTilBloomVandDistribuering.add("37 sek");
        antalSekunderTilBloomVandDistribuering.add("38 sek");
        antalSekunderTilBloomVandDistribuering.add("39 sek");
        antalSekunderTilBloomVandDistribuering.add("40 sek");
        antalSekunderTilBloomVandDistribuering.add("41 sek");
        antalSekunderTilBloomVandDistribuering.add("42 sek");
        antalSekunderTilBloomVandDistribuering.add("43 sek");
        antalSekunderTilBloomVandDistribuering.add("44 sek");
        antalSekunderTilBloomVandDistribuering.add("45 sek");
        antalSekunderTilBloomVandDistribuering.add("46 sek");
        antalSekunderTilBloomVandDistribuering.add("47 sek");
        antalSekunderTilBloomVandDistribuering.add("48 sek");
        antalSekunderTilBloomVandDistribuering.add("49 sek");
        antalSekunderTilBloomVandDistribuering.add("50 sek");
        antalSekunderTilBloomVandDistribuering.add("51 sek");
        antalSekunderTilBloomVandDistribuering.add("52 sek");
        antalSekunderTilBloomVandDistribuering.add("53 sek");
        antalSekunderTilBloomVandDistribuering.add("54 sek");
        antalSekunderTilBloomVandDistribuering.add("55 sek");
        antalSekunderTilBloomVandDistribuering.add("56 sek");
        antalSekunderTilBloomVandDistribuering.add("57 sek");
        antalSekunderTilBloomVandDistribuering.add("58 sek");
        antalSekunderTilBloomVandDistribuering.add("59 sek");
        antalSekunderTilBloomVandDistribuering.add("60 sek");
        antalSekunderTilBloomVandDistribuering.add("61 sek");

    }


    @Override
    public void onClick(View v) {
        if (v == buttonNext4){
            progressBarStatus += 20;
            progressBar.setProgress(progressBarStatus);

            FinalBroeg finalBroeg = new FinalBroeg();
            finalBroeg.setArguments(bundle);

            getFragmentManager().beginTransaction()
                    //.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.broegFragmentetIActivity, finalBroeg)
                    .addToBackStack(null)
                    .commit();

        }
        else if (v == buttonTilbage4){
            HvorMegetVandTilBloomFragment hvorMegetVandTilBloomFragment = new HvorMegetVandTilBloomFragment();
            hvorMegetVandTilBloomFragment.setArguments(bundle);
            getFragmentManager().beginTransaction()
                    //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
                    .replace(R.id.broegFragmentetIActivity, hvorMegetVandTilBloomFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}