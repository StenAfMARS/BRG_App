package grp02.brg_app.View.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

public class OnSaveBryg extends Fragment {

    private Executor bg;
    private Handler ui;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View onSaveBryg = inflater.inflate(R.layout.fragment_on_save_bryg, container, false);
        bg = Executors.newSingleThreadExecutor();
        ui = new Handler(Looper.getMainLooper());
        LottieAnimationView osAV = onSaveBryg.findViewById(R.id.OS_itemSavedAV);
        osAV.setVisibility(View.GONE);

        showAnimation(osAV);

        return onSaveBryg;
    }

    public void showAnimation(LottieAnimationView view) {
        bg.execute(() -> {
            ui.post(() -> {
                view.setVisibility(View.VISIBLE);
            });

            try {
                TimeUnit.SECONDS.sleep(4);

                Intent intent = new Intent(BroegActivity1.context, MainActivity.class);
                startActivity(intent);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}