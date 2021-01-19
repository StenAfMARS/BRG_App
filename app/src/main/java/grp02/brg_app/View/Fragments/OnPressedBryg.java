package grp02.brg_app.View.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;


import android.os.Looper;

import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import grp02.brg_app.Control.BLE.Blessed.WriteType;
import grp02.brg_app.Control.BLE.BluetoothHandler;
import grp02.brg_app.R;
import grp02.brg_app.View.MainActivity;

public class OnPressedBryg extends Fragment {

    private Executor bg;
    private Handler ui;
    private Context context;
    public OnPressedBryg(Context context){this.context = context;}
    private BluetoothHandler BLEhandler = BluetoothHandler.getInstance(MainActivity.context);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View onPressedBryg = inflater.inflate(R.layout.fragment_on_pressed_bryg, container, false);
        bg = Executors.newSingleThreadExecutor();
        ui = new Handler(Looper.getMainLooper());
        LottieAnimationView OPAV = onPressedBryg.findViewById(R.id.OP_coffeebeansAV);
        OPAV.setVisibility(View.GONE);

        BLEhandler.writeToCharacteristik("brew", UUID.fromString("4fafc201-1fb5-459e-8fcc-c5c9c331914b"), UUID.fromString("beb5483e-36e1-4688-b7f5-ea07361b26a8"), WriteType.WITH_RESPONSE);

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

                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


}