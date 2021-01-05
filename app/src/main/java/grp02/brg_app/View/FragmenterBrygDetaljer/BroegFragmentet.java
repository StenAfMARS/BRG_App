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

public class BroegFragmentet extends Fragment implements View.OnClickListener {


    List<String> gramKaffe = new ArrayList<>();
    String navn;
    double gramKaffeObjekt;
    TextView hvorMangeKramKaffe;
    ScrollChoice scrollChoice;
    Button buttonNext, buttonTilbage;
    ProgressBar progressBar;
    int progressBarStatus;
    Bundle bundle = new Bundle();

    @Override
    public View onCreateView(LayoutInflater i,ViewGroup container,Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_hvor_meget_kaffe,container, false);

        Bundle bundleArg = getArguments();
        if (bundleArg != null) {
            navn = bundleArg.getString("navnpåBrygObjekt");
        }
        bundle.putString("navnpåBrygObjekt", navn);

        System.out.println("Start fragment");
        System.out.println("Fået fra bundle, navn: " + navn);


        progressBar = rod.findViewById(R.id.progressBar);

        buttonNext = rod.findViewById(R.id.buttonNext);
        buttonNext.setText("NÆSTE");
        buttonNext.setOnClickListener(this);

        buttonTilbage = rod.findViewById(R.id.buttonTilbage);
        buttonTilbage.setText("TILBAGE");
        buttonTilbage.setOnClickListener(this);

        hvorMangeKramKaffe = rod.findViewById(R.id.textView);
        hvorMangeKramKaffe.setText("HVOR MANGE GRAM KAFFE VIL DU HAVE?");

        scrollChoice = rod.findViewById(R.id.scroll_choice);
        loadDeForskelligeMængder();



        scrollChoice.addItems(gramKaffe,50); //default index, så den er på "60"
        gramKaffeObjekt = 60;
        bundle.putDouble("gramKaffeObjekt", gramKaffeObjekt);
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                gramKaffeObjekt = Double.parseDouble(name.substring(0, name.length()-2));
               bundle.putDouble("gramKaffeObjekt", gramKaffeObjekt);
                System.out.println("Gram Kaffe: "+gramKaffeObjekt);
            }
        });
        return rod;
    }
    private void loadDeForskelligeMængder(){
        gramKaffe.add("55.0 G");
        gramKaffe.add("55.1 G");
        gramKaffe.add("55.2 G");
        gramKaffe.add("55.3 G");
        gramKaffe.add("55.4 G");
        gramKaffe.add("55.5 G");
        gramKaffe.add("55.6 G");
        gramKaffe.add("55.7 G");
        gramKaffe.add("55.8 G");
        gramKaffe.add("55.9 G");

        gramKaffe.add("56.0 G");
        gramKaffe.add("56.1 G");
        gramKaffe.add("56.2 G");
        gramKaffe.add("56.3 G");
        gramKaffe.add("56.4 G");
        gramKaffe.add("56.5 G");
        gramKaffe.add("56.6 G");
        gramKaffe.add("56.7 G");
        gramKaffe.add("56.8 G");
        gramKaffe.add("56.9 G");

        gramKaffe.add("57.0 G");
        gramKaffe.add("57.1 G");
        gramKaffe.add("57.2 G");
        gramKaffe.add("57.3 G");
        gramKaffe.add("57.4 G");
        gramKaffe.add("57.5 G");
        gramKaffe.add("57.6 G");
        gramKaffe.add("57.7 G");
        gramKaffe.add("57.8 G");
        gramKaffe.add("57.9 G");

        gramKaffe.add("58.0 G");
        gramKaffe.add("58.1 G");
        gramKaffe.add("58.2 G");
        gramKaffe.add("58.3 G");
        gramKaffe.add("58.4 G");
        gramKaffe.add("58.5 G");
        gramKaffe.add("58.6 G");
        gramKaffe.add("58.7 G");
        gramKaffe.add("58.8 G");
        gramKaffe.add("58.9 G");

        gramKaffe.add("59.0 G");
        gramKaffe.add("59.1 G");
        gramKaffe.add("59.2 G");
        gramKaffe.add("59.3 G");
        gramKaffe.add("59.4 G");
        gramKaffe.add("59.5 G");
        gramKaffe.add("59.6 G");
        gramKaffe.add("59.7 G");
        gramKaffe.add("59.8 G");
        gramKaffe.add("59.9 G");

        gramKaffe.add("60.0 G");
        gramKaffe.add("60.1 G");
        gramKaffe.add("60.2 G");
        gramKaffe.add("60.3 G");
        gramKaffe.add("60.4 G");
        gramKaffe.add("60.5 G");
        gramKaffe.add("60.6 G");
        gramKaffe.add("60.7 G");
        gramKaffe.add("60.8 G");
        gramKaffe.add("60.9 G");

        gramKaffe.add("61.0 G");
        gramKaffe.add("61.1 G");
        gramKaffe.add("61.2 G");
        gramKaffe.add("61.3 G");
        gramKaffe.add("61.4 G");
        gramKaffe.add("61.5 G");
        gramKaffe.add("61.6 G");
        gramKaffe.add("61.7 G");
        gramKaffe.add("61.8 G");
        gramKaffe.add("61.9 G");

        gramKaffe.add("62.0 G");
        gramKaffe.add("62.1 G");
        gramKaffe.add("62.2 G");
        gramKaffe.add("62.3 G");
        gramKaffe.add("62.4 G");
        gramKaffe.add("62.5 G");
        gramKaffe.add("62.6 G");
        gramKaffe.add("62.7 G");
        gramKaffe.add("62.8 G");
        gramKaffe.add("62.9 G");

        gramKaffe.add("63.0 G");
        gramKaffe.add("63.1 G");
        gramKaffe.add("63.2 G");
        gramKaffe.add("63.3 G");
        gramKaffe.add("63.4 G");
        gramKaffe.add("63.5 G");
        gramKaffe.add("63.6 G");
        gramKaffe.add("63.7 G");
        gramKaffe.add("63.8 G");
        gramKaffe.add("63.9 G");

        gramKaffe.add("64.0 G");
        gramKaffe.add("64.1 G");
        gramKaffe.add("64.2 G");
        gramKaffe.add("64.3 G");
        gramKaffe.add("64.4 G");
        gramKaffe.add("64.5 G");
        gramKaffe.add("64.6 G");
        gramKaffe.add("64.7 G");
        gramKaffe.add("64.8 G");
        gramKaffe.add("64.9 G");

        gramKaffe.add("65.0 G");
        gramKaffe.add("65.1 G");
        gramKaffe.add("65.2 G");
        gramKaffe.add("65.3 G");
        gramKaffe.add("65.4 G");
        gramKaffe.add("65.5 G");
        gramKaffe.add("65.6 G");
        gramKaffe.add("65.7 G");
        gramKaffe.add("65.8 G");
        gramKaffe.add("65.9 G");
        gramKaffe.add("66.0 G");
    }


    @Override
    public void onClick(View v) {
        if (v == buttonNext){
            progressBarStatus +=20;
            progressBar.setProgress(progressBarStatus);
            HvorMegetVandFragment hvorMegetVandFragment = new HvorMegetVandFragment();
            hvorMegetVandFragment.setArguments(bundle);
            getFragmentManager().beginTransaction()
                  //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.broegFragmentetIActivity, hvorMegetVandFragment)
                    .addToBackStack(null)
                    .commit();
        } else if (v == buttonTilbage){
            StartBroeg startBroeg = new StartBroeg();
            startBroeg.setArguments(bundle);
            getFragmentManager().beginTransaction()
                    //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
                    .replace(R.id.broegFragmentetIActivity, startBroeg)
                    .addToBackStack(null)
                    .commit();
        }
    }
}