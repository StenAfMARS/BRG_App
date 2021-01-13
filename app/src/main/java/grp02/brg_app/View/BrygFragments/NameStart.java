package grp02.brg_app.View.BrygFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import grp02.brg_app.Control.RecipeFactoryController;
import grp02.brg_app.R;

public class NameStart extends Fragment implements View.OnClickListener {

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

        editText = rod.findViewById(R.id.navnPaaBroeggen);
        editText.setHint("Navn på din brøg");
        System.out.println("Start fragment");


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
                System.out.println("Navnet: " + navn);
                RecipeFactoryController.getInstance().setRecipeName(navn);
                GroundCoffee groundCoffee = new GroundCoffee();
                getFragmentManager().beginTransaction()
                        //.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                        .replace(R.id.broegFragmentetIActivity, groundCoffee)
                        .addToBackStack(null)
                        .commit();
            }
        }
    }
}