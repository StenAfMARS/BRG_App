package grp02.brg_app.View.BrygFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.concurrent.TimeUnit;

import grp02.brg_app.R;
import grp02.brg_app.View.BroegActivity1;
import grp02.brg_app.View.MainActivity;

public class OnPressedBryg extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View onPressedBryg = inflater.inflate(R.layout.fragment_on_pressed_bryg, container, false);

        try {
            TimeUnit.SECONDS.sleep(10);

            Intent intent = new Intent(BroegActivity1.context, MainActivity.class);
            startActivity(intent);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Inflate the layout for this fragment
        return onPressedBryg;
    }


}