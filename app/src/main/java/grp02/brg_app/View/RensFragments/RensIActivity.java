package grp02.brg_app.View.RensFragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import grp02.brg_app.R;


public class RensIActivity extends Fragment implements View.OnClickListener {

    TextView textView;
    Button clean;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_rens_i_activity, container, false);

        clean = rod.findViewById(R.id.clean);
        clean.setOnClickListener(this);

        textView = rod.findViewById(R.id.textView5);
        textView.setText("Rens din enhed");


        return rod;
    }


    @Override
    public void onClick(View v) {
        if (v == clean) {
            AlertDialog.Builder build= new AlertDialog.Builder(getActivity());
            build.setMessage(Html.fromHtml("<b>For at udføre rens af enhed:</b><br>- Fyld enheden med 1 del eddike, 1 del vand<br>Eller<br>- Fyld enheden med en given kafferens og følg instruktioner herom<br><br>Klik 'OK' når enheden er fyldt", 1));
            build.setPositiveButton("OK", (dialog, which) -> getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.rensFragment, new Rens())
                    .addToBackStack(null)
                    .commit());
            build.setNegativeButton("EXIT", (dialog, which) -> {
            });
            build.show();
            }
        }
    }
