package grp02.brg_app.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import grp02.brg_app.R;
import grp02.brg_app.View.FragmenterBrygDetaljer.NameStart;


public class BroegActivity1 extends AppCompatActivity{

    @SuppressLint("StaticFieldLeak")
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broeg1);

        context = this;

        if (savedInstanceState == null) {
            Fragment fragment = new NameStart();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.broegFragmentetIActivity, fragment)  // tom container i layout
                    .commit();


            // Navigation
            // ##########################################################
            BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
            // Set nav highlighted button
            bottomNav.setSelectedItemId(R.id.nav_BroegBtn);
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
                            startActivity(new Intent(getApplicationContext(), IndstillingerActivity1.class));
                            overridePendingTransition(0, 0);
                            return true;
                    }
                    return false;
                }
            });
            // ##########################################################

        }
    }


}