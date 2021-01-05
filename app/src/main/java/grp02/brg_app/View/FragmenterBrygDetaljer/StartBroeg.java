package grp02.brg_app.View.FragmenterBrygDetaljer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import grp02.brg_app.Model.BrygObjekt;
import grp02.brg_app.R;

public class StartBroeg extends Fragment implements View.OnClickListener {

    TextView textView;
    EditText editText;
    String navn;
    Button buttonNextStart;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_start, container, false);

        buttonNextStart = rod.findViewById(R.id.buttonNextStart);
        buttonNextStart.setText("NÆSTE");
        buttonNextStart.setOnClickListener(this);

        textView = rod.findViewById(R.id.textViewStart);
        textView.setText("Hvad skal din brøg hedde");

        Bundle bundleArg = getArguments();
        if (bundleArg != null) {
            navn = bundleArg.getString("navnpåBrygObjekt");
        }

        editText = rod.findViewById(R.id.navnPaaBroeggen);
        editText.setHint("Navn på din brøg");
        System.out.println("Start fragment");
        System.out.println("Navnet: " + navn);

        return rod;
    }


    @Override
    public void onClick(View v) {
        if (v == buttonNextStart) {
            String navn1 = editText.getText().toString();
            if (navn1.isEmpty()){
            editText.setError("Husk at indtaste et navn");
            } else {
                navn = editText.getText().toString();
                BroegFragmentet broegFragmentet = new BroegFragmentet();
                Bundle bundle = new Bundle();
                bundle.putString("navnpåBrygObjekt", navn);
                broegFragmentet.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        //.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                        .replace(R.id.broegFragmentetIActivity, broegFragmentet)
                        .addToBackStack(null)
                        .commit();
            }
        }
    }
}