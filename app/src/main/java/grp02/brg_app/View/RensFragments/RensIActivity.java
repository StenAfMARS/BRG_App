package grp02.brg_app.View.RensFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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
                getFragmentManager().beginTransaction()
                        //.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                        .replace(R.id.rensFragment, new Rens())
                        .addToBackStack(null)
                        .commit();
            }
        }
    }
