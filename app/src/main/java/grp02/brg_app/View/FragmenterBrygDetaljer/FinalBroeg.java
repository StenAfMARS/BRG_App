package grp02.brg_app.View.FragmenterBrygDetaljer;

import android.content.Intent;
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
import grp02.brg_app.View.BroegActivity1;
import grp02.brg_app.View.MainActivity;

public class FinalBroeg extends Fragment implements View.OnClickListener {

    String navn;
    double gramKaffe;
    int mlVand, antalSekunder, antalSekunderTilBloomVandDistribuering, mlVandTilBloom;
    TextView textView, navnet, gramKaffe1, mlVand2, antalSekunder3, mlVandTilBloom4, antalSekunderTilBloomVandDistribuering5;
    Button buttonNext1, buttonTilbage1;
    ProgressBar progressBar;
    int progressBarStatus = 100;
    Bundle bundle = new Bundle();

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container,
                             Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_final_broeg, container, false);

        Bundle bundleArg = getArguments();
        if (bundleArg != null){
            navn = bundleArg.getString("navnpåBrygObjekt");
            gramKaffe = bundleArg.getDouble("gramKaffeObjekt");
            mlVand = bundleArg.getInt("mlVandObjekt");
            antalSekunder = bundleArg.getInt("antalSekunderObjekt");
            mlVandTilBloom = bundleArg.getInt("mlVandTilBloomObjekt");
            antalSekunderTilBloomVandDistribuering = bundleArg.getInt("antalSekunderTilBloomVandDistribueringObjekt");
        }
        bundle.putString("navnpåBrygObjekt", navn);
        bundle.putDouble("gramKaffeObjekt", gramKaffe);
        bundle.putInt("mlVandObjekt", mlVand);
        bundle.putInt("antalSekunderObjekt", antalSekunder);
        bundle.putInt("mlVandTilBloomObjekt", mlVandTilBloom);
        bundle.putInt("antalSekunderTilBloomVandDistribueringObjekt", antalSekunderTilBloomVandDistribuering);

        System.out.println("Fået fra bundle, navn: " + navn);
        System.out.println("Fået fra bundle, kaffe: " + gramKaffe);
        System.out.println("Fået fra bundle, ml vand: " + mlVand);
        System.out.println("Fået fra bundle, antal sekunder: " + antalSekunder);
        System.out.println("Fået fra bundle, ml vand til bloom: " + mlVandTilBloom);
        System.out.println("Fået fra bundle, antal sekunder til bloomvandsdistribuering: " + antalSekunderTilBloomVandDistribuering);

        navnet = rod.findViewById(R.id.navn);
        gramKaffe1 = rod.findViewById(R.id.gramKaffe1);
        mlVand2 = rod.findViewById(R.id.mlVand2);
        antalSekunder3 = rod.findViewById(R.id.antalSekunder3);
        mlVandTilBloom4 = rod.findViewById(R.id.mlVandTilBloom4);
        antalSekunderTilBloomVandDistribuering5 = rod.findViewById(R.id.antalSekunderTilBloomVandDistribuering5);

        navnet.setText(navn);
        gramKaffe1.setText(gramKaffe + " g. kaffe");
        mlVand2.setText(mlVand + " ml. vand");
        antalSekunder3.setText(antalSekunder + " sekunder");
        mlVandTilBloom4.setText(mlVandTilBloom + " ml. vand til bloom");
        antalSekunderTilBloomVandDistribuering5.setText(antalSekunderTilBloomVandDistribuering + " sekunder til bloom vandsdistribuering");

        progressBar = rod.findViewById(R.id.progressBarFinal);
        progressBar.setProgress(progressBarStatus);

        buttonNext1 = rod.findViewById(R.id.buttonNextFinal);
        buttonNext1.setText("NÆSTE ->");
        buttonNext1.setOnClickListener(this);

        buttonTilbage1 = rod.findViewById(R.id.buttonTilbageFinal);
        buttonTilbage1.setText("<- TILBAGE");
        buttonTilbage1.setOnClickListener(this);

        textView = rod.findViewById(R.id.textViewFinal);
        textView.setText("Dette er din endelige brøg");

        return rod;
    }


    @Override
    public void onClick(View v) {
        if (v == buttonNext1) {
            BroegActivity1 broegActivity1 = new BroegActivity1();

            broegActivity1.list.add(new BrygObjekt(navn, gramKaffe, mlVand, antalSekunder, mlVandTilBloom, antalSekunderTilBloomVandDistribuering));
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra("navn", navn);
            intent.putExtra("gramKaffe", gramKaffe);
            intent.putExtra("mlVand", mlVand);
            intent.putExtra("antalSekunder", antalSekunder);
            intent.putExtra("mlVandTilBloom", mlVandTilBloom);
            intent.putExtra("antalSekunderTilBloomVandDistribuering", antalSekunderTilBloomVandDistribuering);
            startActivity(intent);

        } else if (v == buttonTilbage1) {
            BloomvandDistribueringFragment bloomvandDistribueringFragment = new BloomvandDistribueringFragment();
            bloomvandDistribueringFragment.setArguments(bundle);
            getFragmentManager().beginTransaction()
                    //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
                    .replace(R.id.broegFragmentetIActivity, bloomvandDistribueringFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}