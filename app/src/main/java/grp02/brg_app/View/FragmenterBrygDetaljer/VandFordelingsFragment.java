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
    Bundle bundle = new Bundle();

    @Override
    public View onCreateView(LayoutInflater i,ViewGroup container,Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_vand_fordelings_tid,container, false);

        //bliver ikke brugt endnu, men vil blive benyttet hvis man vil have vist hvad man valgt af værdi fra forrige fragment
        Bundle bundleArg = getArguments();
        if (bundleArg != null){
            navn = bundleArg.getString("navnpåBrygObjekt");
            gramKaffeObjekt = bundleArg.getDouble("gramKaffeObjekt");
            mlVandObjekt = bundleArg.getInt("mlVandObjekt");
        }
        bundle.putString("navnpåBrygObjekt", navn);
        bundle.putDouble("gramKaffeObjekt", gramKaffeObjekt);
        bundle.putInt("mlVandObjekt", mlVandObjekt);

        System.out.println("Fået fra bundle, navn: " + navn);
        System.out.println("Fået fra bundle, kaffe: " + gramKaffeObjekt);
        System.out.println("Fået fra bundle, ml vand: " + mlVandObjekt);


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
        bundle.putInt("antalSekunderObjekt", antalSekunderObjekt);

        scrollChoice.addItems(antalSekunder,15); //default index, så den er på "60"
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                antalSekunderObjekt = Integer.parseInt(name.substring(0, name.length()-4));
                bundle.putInt("antalSekunderObjekt", antalSekunderObjekt);
                System.out.println("Antal Sekunder: "+antalSekunderObjekt);
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
            progressBarStatus += 20;
            progressBar.setProgress(progressBarStatus);

            //Sende dataen til næste fragment, så man kan se hvad værdi man har valgt
            HvorMegetVandTilBloomFragment hvorMegetVandTilBloomFragment = new HvorMegetVandTilBloomFragment();
            hvorMegetVandTilBloomFragment.setArguments(bundle);

            getFragmentManager().beginTransaction()
                    //.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.broegFragmentetIActivity, hvorMegetVandTilBloomFragment)
                    .addToBackStack(null)
                    .commit();}
        else if (v == buttonTilbage2){
            HvorMegetVandFragment hvorMegetVandFragment = new HvorMegetVandFragment();
            hvorMegetVandFragment.setArguments(bundle);
            getFragmentManager().beginTransaction()
                    //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
                    .replace(R.id.broegFragmentetIActivity, hvorMegetVandFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}