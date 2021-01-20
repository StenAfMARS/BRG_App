package grp02.brg_app.View.HistorikFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;


import android.os.Looper;
import android.os.Handler;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import grp02.brg_app.R;
import grp02.brg_app.View.MainActivity;

public class OnItemDelete extends Fragment {

    private Executor bg;
    private Handler ui;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View onItemDeleteFrag = inflater.inflate(R.layout.fragment_on_item_delete, container, false);

        bg = Executors.newSingleThreadExecutor();
        ui = new Handler(Looper.getMainLooper());
        LottieAnimationView OPAV = onItemDeleteFrag.findViewById(R.id.OD_itemDeleteAV);
        OPAV.setVisibility(View.GONE);

        showAnimation(OPAV);

        return onItemDeleteFrag;
    }

    public void showAnimation(LottieAnimationView view) {
        bg.execute(() -> {
            ui.post(() -> {
                view.setVisibility(View.VISIBLE);
            });

            try {
                TimeUnit.SECONDS.sleep(3);

                Intent intent = new Intent(MainActivity.context, MainActivity.class);
                startActivity(intent);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}