package grp02.brg_app.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import grp02.brg_app.R;

public class IndstillingerActivity1 extends AppCompatActivity implements View.OnClickListener {

    Button bt_BTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indstillinger1);
        bt_BTN = findViewById(R.id.BTN_Bluetooth);
        // Navigation
        // ##########################################################
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        // Set nav highlighted button
        bottomNav.setSelectedItemId(R.id.nav_IndstillingerBtn);
        // Perform ItemSelectedListener
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_HomeBtn:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_BroegBtn:
                        startActivity(new Intent(getApplicationContext(), BroegActivity1.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_HistorikBtn:
                        startActivity(new Intent(getApplicationContext(), HistorikActivity1.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_RensBtn:
                        startActivity(new Intent(getApplicationContext(), RensActivity1.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_IndstillingerBtn:
                        return true;
                }
                return false;
            }
        });
        // ##########################################################

    }
    public void initBTN(View view){
        bt_BTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == bt_BTN){
            bt_BTN.setBackgroundResource(R.drawable.ic_baseline_bluetooth_audio_24);
            bt_BTN.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
        }
    }
}