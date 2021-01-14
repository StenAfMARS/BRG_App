package grp02.brg_app.View.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;


import android.os.Looper;
import android.os.Handler;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import grp02.brg_app.R;
import grp02.brg_app.View.BroegActivity1;
import grp02.brg_app.View.MainActivity;

public class OnPressedBryg extends Fragment {

    private Executor bg;
    private Handler ui;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View onPressedBryg = inflater.inflate(R.layout.fragment_on_pressed_bryg, container, false);
        bg = Executors.newSingleThreadExecutor();
        ui = new Handler(Looper.getMainLooper());
        LottieAnimationView OPAV = onPressedBryg.findViewById(R.id.OP_coffeebeansAV);
        OPAV.setVisibility(View.GONE);

        showAnimation(OPAV);
        // Inflate the layout for this fragment
        return onPressedBryg;
    }

    public void showAnimation(LottieAnimationView view) {
        bg.execute(() -> {
            ui.post(() -> {
                view.setVisibility(View.VISIBLE);
            });

            try {
                TimeUnit.SECONDS.sleep(5);

                Intent intent = new Intent(BroegActivity1.context, MainActivity.class);
                startActivity(intent);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


}