package grp02.brg_app.View.BrygFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import grp02.brg_app.R;

public class OnPressedBryg extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View onPressedBryg = inflater.inflate(R.layout.fragment_on_pressed_bryg, container, false);

        // Inflate the layout for this fragment
        return onPressedBryg;
    }


}