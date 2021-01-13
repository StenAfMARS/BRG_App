package grp02.brg_app.View.RensFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import grp02.brg_app.R;

public class Rens extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rens = inflater.inflate(R.layout.fragment_rens, container, false);

        // Inflate the layout for this fragment
        return rens;
    }
}