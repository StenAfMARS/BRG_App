package grp02.brg_app.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.concurrent.TimeUnit;

import grp02.brg_app.R;

public class OnSaveBryg extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View onSaveBryg = inflater.inflate(R.layout.fragment_on_save_bryg, container, false);

        showContent();
        // Inflate the layout for this fragment
        return onSaveBryg;
    }

    private void showContent() {
        try {
            TimeUnit.SECONDS.sleep(5);

            Intent intent = new Intent(BroegActivity1.context, MainActivity.class);
            startActivity(intent);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}